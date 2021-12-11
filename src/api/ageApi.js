import axiosClient from "./axiosClient";

const ageApi = {
  getAll: (params) => {
    const url = '/age';
    return axiosClient.get(url, { params });
  },

  get: (id) => {
    const url = `/age/${id}`;
    return axiosClient.get(url);
  },

  create: (request) => {
    const url = '/age';
    return axiosClient.post(url, request);
  },

  update: (request) => {
    
    const url = `/age/${request.id}`;
    axiosClient.put(url, request);
  },

  delete: (request) => {
    const url = `/age/${request.id}`;
    axiosClient.delete(url);
  }
}

export default ageApi; 