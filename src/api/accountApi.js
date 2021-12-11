import axiosClient from "./axiosClient";

const accountApi = {
  getAll: (params) => {
    const url = '/account';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/account/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/account';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/account/${request.id}`;
    axiosClient.put(url, request);
  },

  delete: (request) => {
    const url = `/account/${request.id}`;
    axiosClient.delete(url);
  }
}
//đợi tí để tạo 1 branch cho ông
export default accountApi; 