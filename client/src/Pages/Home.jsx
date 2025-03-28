import React, { useState, useEffect } from 'react';
import './Home.css';
import { getAllBooks } from '../Services/bookService';

const Home = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch books when component mounts
    const fetchBooks = async () => {
      try {
        setLoading(true);
        const response = await getAllBooks();
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
            {books.length > 0 ? (
              books.map((book) => (
                <div className="book-card" key={book.id}>
                  <div className="book-thumbnail"></div>
                  <h3>{book.bookName}</h3>
                  <p>{book.author ? `${book.author.firstName} ${book.author.lastName}` : 'Author Unknown'}</p>
                  <p>Price: ${book.price ? book.price.toFixed(2) : '0.00'}</p>
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