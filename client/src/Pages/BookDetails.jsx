import React, { useState, useEffect } from 'react';
import { getBookById } from '../Services/bookService';
import { useParams, useNavigate } from 'react-router-dom';
import './BookDetails.css';

const BookDetails = () => {
  const { isbn } = useParams();
  const [book, setBook] = useState(null); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBook = async () => {
      try {
        setLoading(true);
        const response = await getBookById(isbn); 
        
        // Detailed logging
        console.log('Raw response:', response);
        console.log('Book data:', response.data);
        
        console.log('Book:', response.data);
        console.log('Author:', response.data.author);
        console.log('Author First Name:', response.data.author?.firstName);
        console.log('Author Last Name:', response.data.author?.lastName);
        console.log('Book Description:', response.data.bookDescription);

        setBook(response.data);
        setLoading(false);
      } catch (err) {
        console.error('Error fetching book details:', err);
        setError('Failed to load book. Please try again later.');
        setLoading(false);
      }
    };

    fetchBook(); 
  }, [isbn]); 

  return (
    <div className="book-details-container">
      {loading && <p>Loading book details...</p>}
      
      {error && <p className="error-message">{error}</p>}
      
      {!loading && !error && book && (
        <div className="book-details">
          <button className="back-button" onClick={() => navigate('/')}>
            Return to Home
          </button>
          <div className="book-content"> 
            <div className="book-image">
              <div className="book-thumbnail"></div>
            </div>
            <div className="book-info">
              <h1>{book.bookName}</h1>
              <p className="author">
                By: {book.author ? `${book.author.firstName || 'Unknown'} ${book.author.lastName || ''}`.trim() : 'Author Unknown'}
              </p>
              <p className="price">
                Price: ${book.price ? book.price.toFixed(2) : '0.00'}
              </p>
              <div className="description">
                <h3>Description:</h3>
                <p>{book.bookDescription || 'Description Not Available'}</p>
              </div>
              <button className="addtocart">Add to Cart</button>
            </div>
          </div>
        </div>
      )}
      {!loading && !error && !book && (
        <div className="not-found">
          <p>Book not found</p>
          <button onClick={() => navigate('/')}>Return to Home</button>
        </div>
      )}
    </div>
  );
};

export default BookDetails;