import axiosClient from "./axiosClient";

const orderQuanAoApi = {
  getAll: (params) => {
    const url = '/order_quan_ao';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/order_quan_ao/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/order_quan_ao';
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

export default orderQuanAoApi; 