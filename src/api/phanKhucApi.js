import axiosClient from "./axiosClient";

const phanKhucApi = {
  getAll: (params) => {
    const url = '/phan_khuc';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/phan_khuc/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/phan_khuc';
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