import apiClient from './apiClient';
//fetches browse endpoints from backend

export const getBooksByGenre =(genre)=>{
    return apiClient.get(`/browse/genre/${genre}`);
}
export const getTopSellingBooks=()=>{
    return apiClient.get('/browse/top-selling');
}
export const getBooksByRating=(rating)=>{
    return apiClient.get(`/browse/rating/${rating}`);
}
export const applyDiscount = (publisher, discount) => {
    return apiClient.post('/browse/discount', { publisher, discount });
};