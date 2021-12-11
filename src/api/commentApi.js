import axiosClient from "./axiosClient";

const commentApi = {
  getAll: (params) => {
    const url = '/comment';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/comment/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/comment';
    return axiosClient.post(url, request);
  },

//   update: (request) => {
    
//     const url = `/product/${request.id}`;
//     axiosClient.put(url, request);
//   },

//   delete: (request) => {
//     const url = `/product/${request.id}`;
//     axiosClient.delete(url);
//   }
}

export default commentApi; 