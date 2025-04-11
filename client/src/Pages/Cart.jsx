import React, { useEffect, useState } from 'react';
import './Cart.css';

const Cart = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [books, setBooks] = useState([]);
  const [totals, setTotals] = useState({ subtotal: 0, tax: 0, total: 0 });

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      setUsername(user.username);
      setIsLoggedIn(true);
    }
  }, []);

  useEffect(() => {
    if (isLoggedIn) {
      fetchBooks();
      fetchTotals();
    }
  }, [isLoggedIn]);

  const fetchBooks = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/cart/${username}/books`);
      const data = await response.json();
      setBooks(data);
    } catch (error) {
      console.error('Error fetching cart books:', error);
    }
  };

  const fetchTotals = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/cart/${username}/subtotal`);
      const data = await response.json();
      setTotals(data);
    } catch (error) {
      console.error('Error fetching totals:', error);
    }
  };

  const handleRemove = async (isbn) => {
    try {
      await fetch(`http://localhost:8080/api/cart/delete`, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, isbn }),
      });
      fetchBooks(); // Refresh cart
      fetchTotals(); // Refresh totals
    } catch (error) {
      console.error('Error removing book from cart:', error);
    }
  };

  if (!isLoggedIn) {
    return (
      <div className="cart-page-wrapper">
        <div className="cart-login-box">
          <h2>Please log in to view your cart</h2>
          <a href="/profile">
            <button>Go to Login</button>
          </a>
        </div>
      </div>
    );
  }

  return (
    <div className="cart-page-wrapper">
      <div className="cart-container">
        <h2>Your Shopping Cart</h2>
        {books.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          <>
            <ul className="cart-book-list">
              {books.map((book, idx) => (
                <li key={idx} className="cart-book-item">
                  <div>
                    <strong>{book.bookName}</strong> by {book.author.firstName} {book.author.lastName}
                    <p>Price: ${book.price.toFixed(2)}</p>
                  </div>
                  <button onClick={() => handleRemove(book.isbn)}>Remove</button>
                </li>
              ))}
            </ul>

            <div className="cart-summary">
              <p>Subtotal: ${totals.subtotal.toFixed(2)}</p>
              <p>Tax (7%): ${totals.tax.toFixed(2)}</p>
              <p><strong>Total: ${totals.total.toFixed(2)}</strong></p>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default Cart;
