export const pairScooter = async (vehicleCode: string, token: string): Promise<void> => {
  const res = await fetch(
    `https://europe-west3-coscooter-eu-staging.cloudfunctions.net/pair?apiKey=${token}`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({vehicleCode}),
    },
  );

  if (!res.ok) {
    const text = await res.text();
    throw new Error(`Failed to pair scooter: ${text}`);
  }
}
