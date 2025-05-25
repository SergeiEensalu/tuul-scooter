type ResultSuccess = { success: true };
type ResultError = { success: false; message: string; reason?: string };
export type ApiResult = ResultSuccess | ResultError;
