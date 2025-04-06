import apiClient from './apiClient';
//fetches book/rating endpoints from backend

export const getAllBooks =()=>{
    return apiClient.get('/api/books/All');
}
export const getBookById = (id) => {
    return apiClient.get(`/api/books/id/${id}`);
}
export const getRatingById=(id)=>{
  return apiClient.get(`/api/rating/getRatingAvg/${id}`);
}
export const getCommentsById=(id)=>{
  return apiClient.get(`/api/rating/getAllComment/${id}`);
}
export const getAvgRatingById=(id)=>{
  return apiClient.get(`/api/rating/getRatingAvg/${id}`);
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

export const searchBooks = async (query) => {
    try {
      if (!isNaN(query)) {
        try {
          const bookByIdResponse = await getBookById(query);
          if (bookByIdResponse && bookByIdResponse.data) {
    
            return { data: [bookByIdResponse.data] };
          }
        } catch (err) {
          console.log("ID search failed, continuing");
        }
      }
      
      try {
        const bookNameResponse = await getBookByName(query);
        if (bookNameResponse && bookNameResponse.data) {
          return { data: [bookNameResponse.data] };
        }
      } catch (err) {
        console.log("Title search failed, continuing");
      }
      
      try {
        const firstNameResponse = await getBookByFirstName(query);
        if (firstNameResponse && firstNameResponse.data && firstNameResponse.data.length > 0) {
          return firstNameResponse;
        }
      } catch (err) {
        console.log("First name search failed, continuing");
      }
      
      try {
        const lastNameResponse = await getBookByLastName(query);
        if (lastNameResponse && lastNameResponse.data && lastNameResponse.data.length > 0) {
          return lastNameResponse;
        }
      } catch (err) {
        console.log("Last name search failed, continuing");
      }
      
      return { data: [] };
    } catch (error) {
      console.error("Error in search function:", error);
      throw error;
    }
  };