import React, { useState, useEffect } from 'react';
import './Cart.css';
// import { getCartItems, removeFromCart } from '../Services/cartService';

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch cart items when component mounts
    const fetchCartItems = async () => {
      try {
        setLoading(true);
        const response = await getCartItems();
        setCartItems(response.data);
        setLoading(false);
      } catch (err) {
        console.error('Error fetching cart items:', err);
        setError('Failed to load cart items. Please try again later.');
        setLoading(false);
      }
    };

    fetchCartItems();
  }, []);

  // Function to handle item removal from the cart
  const handleRemoveFromCart = async (itemId) => {
    try {
      await removeFromCart(itemId);
      // Refresh the cart items after removal
      const updatedItems = cartItems.filter(item => item.id !== itemId);
      setCartItems(updatedItems);
    } catch (err) {
      console.error('Error removing item from cart:', err);
      setError('Failed to remove item from cart. Please try again later.');
    }
  };

  return (
    <div className="cart-container">
      <h2>Your Cart</h2>
      
      {loading && <p>Loading cart items...</p>}
      
      {error && <p className="error-message">{error}</p>}
      
      {!loading && !error && (
        <div className="cart-items">
          {cartItems.length ? (
            cartItems.map((item) => (
              <div key={item.id} className="cart-item">
                <h3>{item.bookName}</h3>
                <p>Price: ${item.price.toFixed(2)}</p>
                <button onClick={() => handleRemoveFromCart(item.id)}>Remove</button>
              </div>
            ))
          ) : (
            <p>Your cart is empty.</p>
          )}
        </div>
      )}
    </div>
  );
}


export default Cart;

