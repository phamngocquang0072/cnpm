import axiosClient from "./axiosClient";

const phieuXuatApi = {
  getAll: (params) => {
    const url = '/phieu_xuat';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/phieu_xuat/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/phieu_xuat';
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

export default phieuXuatApi; 