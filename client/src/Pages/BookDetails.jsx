import React, { useState, useEffect } from 'react';
import { getBookById,getRatingById,getCommentsById} from '../Services/bookService';
import { useParams, useNavigate } from 'react-router-dom';
import StarRating from '../Components/StarRating/StarRating';
import './BookDetails.css';

const BookDetails = () => {
  const { isbn } = useParams();
  const [book, setBook] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [ratings, setRatings] = useState({ average: 0, count: 0 });
  const [comments, setComments] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBookData = async () => {
      try {
        setLoading(true);
        
  
        const bookResponse = await getBookById(isbn);
        setBook(bookResponse.data);
        
       
        const ratingResponse = await getRatingById(isbn);
        setRatings({
          average: ratingResponse.data.average || 0,
          count: ratingResponse.data.count || 0
        });
        
        
        const commentsResponse = await getCommentsById(isbn);
        setComments(commentsResponse.data || []);
        
        setLoading(false);
      } catch (err) {
        console.error('Error fetching book details:', err);
        setError('Failed to load book. Please try again later.');
        setLoading(false);
      }
    };

    fetchBookData();
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

          {/* Ratings section */}
          <div className="rating-section">
            <h2>Ratings</h2>
            <div className="rating-bottom">
            {ratings.count > 0 ? (
              <StarRating 
                averageRating={ratings.average} 
                ratingCount={ratings.count} 
              />
            ) : (
              <p className="no-ratings-message">No ratings yet</p>
            )}
          </div>
          </div>

          {/* Comments section */}
          <div className="comments-section">
            <h2>Comments</h2>
            <div className="comments-list">
              {comments.length > 0 ? (
                comments.map((comment, index) => (
                  <div key={index} className="comment">
                     <p className="comment-user">{comment.username || 'Anonymous'}</p>
                     <p className="comment-text">{comment.text}</p>
                  </div>
                ))
              ) : (
                <p className="no-comments-message">No comments yet.</p>
              )}
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