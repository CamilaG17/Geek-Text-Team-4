import React, { useState, useEffect } from 'react';
import './Home.css';
import { getAllBooks, getRatingById } from '../Services/bookService';
import { useNavigate } from 'react-router-dom';
import StarRating from '../Components/StarRating/StarRating';

const Home = () => {
  const [books, setBooks] = useState([]);// store books
  const [loading, setLoading] = useState(true);//loading state
  const [error, setError] = useState(null);// error state
  const [ratings, setRatings] = useState({}); // Store ratings for each book
  const navigate = useNavigate();
  const [username, setUsername] = useState('');

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      setUsername(user.username);
    }
  }, []);
  const handleAddToCart = async (isbn) => {
  if (!username) {
    alert("Please log in to add books to your cart.");
    return;
  }

  try {
    const response = await fetch("http://localhost:5500/api/cart/add", {
      
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      credentials: "include",
      body: JSON.stringify({ username, isbn })
    });

    if (response.ok) {
      alert("Book added to cart!");
    } else {
      const errText = await response.text();
      console.error("Add to cart failed:", errText); // 👈 show real message
      alert(`Failed to add book: ${errText}`);
    }
  } catch (error) {
    console.error("Network error:", error); // 👈 this prints the real problem
    alert("Something went wrong (check console).");
  }
};



  useEffect(() => {
    const fetchBooks = async () => {
      try {
        setLoading(true); //loading state when waiting for books to be retrieved
        const response = await getAllBooks();
        setBooks(response.data);
        
        //  store ratings in temp
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
                      className="addtocart"
                        onClick={(e) => {
                          e.stopPropagation();
                          handleAddToCart(book.isbn); // ✅ this adds the book
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

export default Home;