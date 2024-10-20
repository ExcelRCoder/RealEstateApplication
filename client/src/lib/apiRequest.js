import axios from "axios";

const apiRequest = axios.create({
  baseURL: "http://localhost:1234",
  
 
  withCredentials: true,

});

export default apiRequest;