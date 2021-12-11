
import axiosClient from "./axiosClient";

const customerApi = {
  getAll: (params) => {
    const url = '/customer';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/customer/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/customer';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/customer/${request.id}`;
    axiosClient.put(url, request);
  },

//   delete: (request) => {
//     const url = `/product/${request.id}`;
//     axiosClient.delete(url);
//   }
}

export default customerApi; 