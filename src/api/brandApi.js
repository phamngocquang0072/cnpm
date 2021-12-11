import axiosClient from "./axiosClient";

const brandApi = {
  getAll: (params) => {
    const url = '/brand';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/brand/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/brand';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/brand/${request.id}`;
    axiosClient.put(url, request);
  },

  delete: (request) => {
    const url = `/brand/${request.id}`;
    axiosClient.delete(url);
  }
}

export default brandApi; 