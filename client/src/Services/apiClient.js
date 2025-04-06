import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:5500',
    headers:{
        'Content-Type': 'application/json'
    }
});
export default apiClient;
//used to fetch backend api end points