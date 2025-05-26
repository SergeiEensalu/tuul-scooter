import {auth} from '../../config/firebase';

export async function fetchWithToken<T>(
  url: string,
  method: 'GET' | 'POST' | 'DELETE' = 'POST',
  body?: unknown
): Promise<T> {
  const user = auth.currentUser;

  if (!user) {
    throw {message: 'User not authenticated'};
  }

  const token = await user.getIdToken();

  const res = await fetch(`${url}?apiKey=${token}`, {
    method,
    headers: {
      'Content-Type': 'application/json',
    },
    body: body ? JSON.stringify(body) : undefined,
  });

  if (!res.ok) {
    throw await res.json().catch(() => ({message: 'Unknown error'}));
  }

  return await res.json();
}
