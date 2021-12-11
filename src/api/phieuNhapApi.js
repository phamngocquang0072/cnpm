import axiosClient from "./axiosClient";

const phieuNhapApi = {
  getAll: (params) => {
    const url = '/phieu_nhap';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/phieu_nhap/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/phieu_nhap';
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

export default phieuNhapApi; 