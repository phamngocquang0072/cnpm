import axiosClient from "./axiosClient";

const roleApi = {
  getAll: (params) => {
    const url = '/role';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/role/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/role';
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

export default roleApi; 