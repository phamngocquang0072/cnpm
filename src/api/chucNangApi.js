import axiosClient from "./axiosClient";

const chucNangApi = {
  getAll: (params) => {
    const url = '/chucnang';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/chucnang/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/chucnang';
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

export default chucNangApi; 