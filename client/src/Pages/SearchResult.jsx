import React, { useState, useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { searchBooks } from '../Services/bookService';
import '../Pages/Home.css';

const SearchResults = () => {
const [searchParams] = useSearchParams();
const query = searchParams.get('q') || '';

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
  } catch (err) {
    console.error('Error searching books:', err);
    setError('Failed to fetch search results. Please try again later.');
    setLoading(false);
  }
};

fetchSearchResults();
}, [query]);

return (
<div className="home-container">
  <div className="featured-section">
    <h2>Search Results for "{query}"</h2>
    
    {loading && <p>Searching scrolls...</p>}
    
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
                <p className="price">Price: ${book.price ? book.price.toFixed(2) : '0.00'}</p>
                <button className="addtocart" onClick={(e) => {e.stopPropagation();}}>Add to Cart</button>
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