import axiosClient from "./axiosClient";

const phanKhucApi = {
  getAll: (params) => {
    const url = '/product';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/product/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/product';
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

export default phanKhucApi; 