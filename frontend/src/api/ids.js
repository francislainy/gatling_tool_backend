import { getAppConfig } from '../config';
import { getUrl } from './api.helpers';

const appConfig = getAppConfig();

export const getStates = async (token, config = {}) => {
  return getUrl('/states', {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getDistricts = async (stateCode, token, config = {}) => {
  return getUrl(`/states/${stateCode}/districts`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getSchools = async ({ orgRefId }, token, config = {}) => {
  return getUrl(`/districts/${orgRefId}/schools`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getSchool = async ({ schoolId }, token, config = {}) => {
  return getUrl(`/schools/${schoolId}`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getTeachers = async ({ schoolId }, token, config = {}) => {
  return getUrl(`/schools/${schoolId}/teachers`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getStudents = async ({ schoolId }, token, config = {}) => {
  return getUrl(`/schools/${schoolId}/students`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getSections = async ({ schoolId }, token, config = {}) => {
  return getUrl(`/schools/${schoolId}/sections`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getRosters = async ({ sectionRefId }, token, config = {}) => {
  return getUrl(`/sections/${sectionRefId}/rosters`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};

export const getAdmins = async ({ orgRefId }, token, config = {}) => {
  return getUrl(`/organizations/${orgRefId}/administrators`, {
    baseUrl: appConfig.showcaseUriBaseIds,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    ...config,
  });
};
