import { getAppConfig } from '../config';

const appConfig = getAppConfig();

export const getUrl = (url, config = {}) => {
  const requestUrl = `${config.baseUrl || ''}${url}`;

  return fetch(requestUrl, {
    method: 'GET',
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
