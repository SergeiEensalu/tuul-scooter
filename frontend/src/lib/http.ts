export async function http<T>(
  url: string,
  method: 'POST' | 'DELETE' | 'GET' = 'POST',
  body?: unknown
): Promise<T> {
  const res = await fetch(url, {
    method,
    headers: {'Content-Type': 'application/json'},
    body: body ? JSON.stringify(body) : undefined,
  });

  if (!res.ok) {
    throw await res.json()
      .catch(() => ({
        message: 'Unknown error'
      }))
  }

  return await res.json();
}
