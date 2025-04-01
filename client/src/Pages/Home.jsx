import React, { useState, useEffect } from 'react';
import './Home.css';
import { getAllBooks } from '../Services/bookService';

const Home = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        setLoading(true);
        const response = await getAllBooks();
        
        // Detailed logging
        console.log('Raw response:', response);
        console.log('Books data:', response.data);
        
        // Log details of each book
        response.data.forEach(book => {
          console.log('Book:', book);
          console.log('Author:', book.author);
          console.log('Author First Name:', book.author?.firstName);
          console.log('Author Last Name:', book.author?.lastName);
        });

        setBooks(response.data);
        setLoading(false);
      } catch (err) {
        console.error('Error fetching books:', err);
        setError('Failed to load books. Please try again later.');
        setLoading(false);
      }
    };

    fetchBooks();
  }, []);

  return (
    <div className="home-container">
      <div className="featured-section">
        <h2>Featured Scrolls</h2>
        
        {loading && <p>Loading books...</p>}
        
        {error && <p className="error-message">{error}</p>}
        
        {!loading && !error && (
          <div className="book-grid">
            {books.length ? (
              books.map((book, index) => (
                <div 
                  className="book-card" 
                  key={book.isbn || `book-${index}`}
                >
                  <div className="book-thumbnail"></div>
                  <h3>{book.bookName}</h3>
                  <p>
                    {/* Detailed fallback for author display */}
                    {book.author 
                      ? `${book.author.firstName || 'Unknown'} ${book.author.lastName || ''}`.trim()
                      : 'Author Unknown'}
                  </p>
                  <p>Price: ${book.price ? book.price.toFixed(2) : '0.00'}</p>
                  <button className="addtocart">Add to Cart</button>
                </div>
              ))
            ) : (
              <p>No books found. Check back soon!</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default Home;