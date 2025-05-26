type ApiSuccess<T> = {
  success: true;
  data: T;
};

type ApiError = {
  success: false;
  message: string;
  reason?: string;
  code?: string;
};

export type ApiResult<T = void> = ApiSuccess<T> | ApiError;