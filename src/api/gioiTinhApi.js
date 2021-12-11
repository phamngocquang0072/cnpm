import axiosClient from "./axiosClient";

const gioiTinhApi = {
  getAll: (params) => {
    const url = '/gioitinh';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/gioitinh/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/gioitinh';
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

export default gioiTinhApi; 