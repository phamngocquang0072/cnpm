import axiosClient from "./axiosClient";

const categoryProductApi = {
  getAll: (params) => {
    const url = '/category_product';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/category_product/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/category_product';
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

export default categoryProductApi; 