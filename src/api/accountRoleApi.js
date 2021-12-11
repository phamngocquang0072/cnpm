import axiosClient from "./axiosClient";

const accountApi = {
  getAll: (params) => {
    const url = '/accountrole';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/accountrole/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/accountrole';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/accountrole/${request.id}`;
    axiosClient.put(url, request);
  },

  delete: (request) => {
    const url = `/accountrole/${request.id}`;
    axiosClient.delete(url);
  }
}
//đợi tí để tạo 1 branch cho ông
export default accountApi; 