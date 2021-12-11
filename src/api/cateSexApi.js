import axiosClient from "./axiosClient";

const cateSexApi = {
  getAll: (params) => {
    const url = '/cate_sex';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/cate_sex/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/cate_sex';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/cate_sex/${request.id}`;
    axiosClient.put(url, request);
  },

  delete: (request) => {
    const url = `/cate_sex/${request.id}`;
    axiosClient.delete(url);
  }
}

export default cateSexApi; 