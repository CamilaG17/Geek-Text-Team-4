import apiClient from './apiClient';


export const getAllBooks =()=>{
    return apiClient.get('/api/books/All');
}
export const getBookById = (id) => {
    return apiClient.get(`/api/books/id/${id}`);
}
export const getBookByName = (bookName) =>{
    return apiClient.get(`/api/title/${bookName}`);
}
export const getBookByFirstName = (firstName) =>{
    return apiClient.get(`/api/books/firstName/${firstName}`);
}
export const getBookByLastName = (lastName) =>{
    return apiClient.get(`/api/books/lastName/${lastName}`);
}
export const createBook =(bookData)=>{
    return apiClient.post('/api/create/book', bookData);
};