import React from 'react';
import '../../Components/StarRating/StarRating.css'

const StarRating = ({isbn, averageRating, ratingCount }) => {
  
  const fullStars = Math.floor(averageRating);
  const hasHalfStar = averageRating % 1 >= 0.5;

  return (
    <div className="star-rating">
      {[1, 2, 3, 4, 5].map((star) => {
        if (star <= fullStars) {
          return <span key={star} className="star filled">★</span>;
        } else if (star === fullStars + 1 && hasHalfStar) {
          return <span key={star} className="star half-filled">★</span>;
        } else {
          return <span key={star} className="star">★</span>;
        }
      })}
      <span className="rating-text">
        ({averageRating.toFixed(1)}, {ratingCount} {ratingCount === 1 ? 'rating' : 'ratings'})
      </span>
    </div>
  );
};

export default StarRating;