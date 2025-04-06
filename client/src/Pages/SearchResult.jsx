import React, { useState, useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { searchBooks } from '../Services/bookService';
import { getRatingById } from '../Services/bookService';
import '../Pages/Home.css';
import StarRating from '../Components/StarRating/StarRating';

const SearchResults = () => {
const [searchParams] = useSearchParams();
const query = searchParams.get('q') || '';
const [ratings, setRatings] = useState({}); 
const [books, setBooks] = useState([]);
const [loading, setLoading] = useState(true);
const [error, setError] = useState(null);
const navigate = useNavigate();

useEffect(() => {
const fetchSearchResults = async () => {
  if (!query) return;
  
  try {
    setLoading(true);
    const response = await searchBooks(query);
    setBooks(response.data);
    setLoading(false);
  const ratingsData = {};
  
  // Fetch ratings for all books
  for (const book of response.data) {
  try {
  const ratingResponse = await getRatingById(book.isbn);
  ratingsData[book.isbn] = {
    average: ratingResponse.data.average || 0,
    count: ratingResponse.data.count || 0
  };
  } catch (err) {
  console.error(`Error fetching rating for book ${book.isbn}:`, err);
  ratingsData[book.isbn] = { average: 0, count: 0 };
  }
  }
  
  // Update ratings state with all fetched data
  setRatings(ratingsData);
  setLoading(false);
  } catch (err) {
  console.error('Error fetching books:', err);
  setError('Failed to load books. Please try again later.');
  setLoading(false);
  }
  };
  
fetchSearchResults();
}, [query]);

return (
  <div className="home-container">
  <div className="featured-section">
  <h2>Science-Fiction Scrolls</h2>
  
  {loading && <p>Loading books...</p>}
  
  {error && <p className="error-message">{error}</p>}
  
  {!loading && !error && (
  <div className="book-grid">
  {books.length ? (
    books.map((book, index) => (
      <div 
        className="book-card" 
        key={book.isbn || `book-${index}`}
        onClick={() => navigate(`/book/${book.isbn}`)}
      >
        <div className="scroll-image"></div>
        <div className="book-info">
          <h3>{book.bookName}</h3>
          <p className="author"> 
            {book.author 
              ? `${book.author.firstName || ''} ${book.author.lastName || ''}`.trim()
              : 'Author Unknown'} 
          </p>
          {ratings[book.isbn] && (
            ratings[book.isbn].count > 0 ? (
              <StarRating 
                averageRating={ratings[book.isbn].average} 
                ratingCount={ratings[book.isbn].count} 
              />
            ) : (
              <p className="no-ratings">No ratings yet</p>
            )
          )}
          <p className="price">Price: ${book.price ? book.price.toFixed(2) : '0.00'}</p>
          <button 
            className="addtocart" //goes to cart on click
            onClick={(e) => {
              e.stopPropagation();
              
            }}
          >
            Add to Cart
          </button>
        </div>
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

export default SearchResults;