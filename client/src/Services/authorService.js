import apiClient from './apiClient';

export const getAllAuthors = () => {
    return apiClient.get('/api/authors/All');
}
export const getAuthorbyId = (id)=> {
    return apiClient.get(`/api/authors/id/${id}`);
}
export const createAuthor =(authorData)=>{
    return apiClient.post('/api/create/author', authorData);
}
// fetches author details from backend