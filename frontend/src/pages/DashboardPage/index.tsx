import React, {useEffect, useState} from 'react';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {FormError} from '../../shared/ui/FormError';
import {pairScooter} from '../../features/pairScooter/pairScooter';
import {getUserActiveVehicleId} from '../../entities/user/getUserActiveVehicle';
import {getVehicleById} from '../../entities/vehicle/getVehicleById';
import {auth} from '../../config/firebase';

export const DashboardPage: React.FC = () => {
  const [vehicleId, setVehicleId] = useState<string | null>(null);
  const [vehicleCode, setVehicleCode] = useState('');
  const [vehicleData, setVehicleData] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    (async () => {
      const id = await getUserActiveVehicleId();

      setVehicleId(id);
      console.log("id", id)
      if (id) {
        const data = await getVehicleById(id);
        setVehicleData(data);
      }
    })();
  }, []);

  const handlePair = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    try {
      const token = await auth.currentUser?.getIdToken();
      if (!token) {
        throw new Error('User not authenticated')
      }
      await pairScooter(vehicleCode, token);
      window.location.reload();
    } catch (err: any) {
      setError(err.message);
    }
  };

  if (vehicleId && vehicleData) {
    return (
      <div className="space-y-4">
        <h1 className="text-xl font-bold">Your Scooter</h1>
        <p>Battery: {vehicleData.soc}%</p>
        <p>Location: {vehicleData.location?.latitude}, {vehicleData.location?.longitude}</p>
        <p>Range: {vehicleData.estimatedRange} km</p>
        <p>Powered: {vehicleData.poweredOn ? 'On' : 'Off'}</p>
        <p>Odometer: {vehicleData.odometer} km</p>
      </div>
    );
  }

  return (
    <form onSubmit={handlePair} className="p-4 space-y-4 max-w-sm mx-auto">
      <h1 className="text-xl font-bold">Pair Your Scooter</h1>
      <Input
        type="text"
        label="Scooter Code"
        placeholder="Enter code"
        value={vehicleCode}
        onChange={(e) => setVehicleCode(e.target.value)}
        required
      />
      <Button type="submit">Pair</Button>
      <FormError message={error || ''}/>
    </form>
  );
};
