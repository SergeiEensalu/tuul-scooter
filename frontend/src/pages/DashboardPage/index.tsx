import React, {useState} from 'react';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {FormError} from '../../shared/ui/FormError';
import {pairScooter} from '../../features/pairScooter/pairScooter';
import {unpairScooter} from '../../features/pairScooter/unpairScooter';
import {useVehicle} from "../../shared/hooks/useVehicle";
import {parseApiError} from '../../shared/utils/parseApiError';

export const DashboardPage: React.FC = () => {
  const {vehicleData, refresh} = useVehicle();
  const [vehicleCode, setVehicleCode] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const handlePair = async () => {
    setError(null);
    setLoading(true);
    const result = await pairScooter(vehicleCode);
    setLoading(false);

    if (!result.success) {
      setError(parseApiError(result));
      return;
    }

    refresh(); // refetch vehicle after pairing
  };

  const handleUnpair = async () => {
    if (!vehicleData?.id) return;
    setError(null);
    setLoading(true);
    const result = await unpairScooter(vehicleData.id);
    setLoading(false);

    if (!result.success) {
      setError(parseApiError(result));
      return;
    }

    refresh(); // refetch after unpair
  };

  if (vehicleData) {
    return (
      <div className="p-4 space-y-4 max-w-sm mx-auto border rounded-md shadow-sm">
        <h1 className="text-xl font-bold">Your Scooter</h1>
        <FormError message={error || ''}/>
        <p><strong>ID:</strong> {vehicleData.id}</p>
        <p><strong>Battery:</strong> {vehicleData.soc}%</p>
        <p><strong>Location:</strong> {vehicleData.location?.latitude}, {vehicleData.location?.longitude}</p>
        <p><strong>Range:</strong> {vehicleData.estimatedRange} km</p>
        <p><strong>Powered:</strong> {vehicleData.poweredOn ? 'On' : 'Off'}</p>
        <p><strong>Odometer:</strong> {vehicleData.odometer} km</p>
        <div className="pt-2">
          <Button onClick={handleUnpair} disabled={loading}>
            {loading ? 'Unpairing...' : 'Unpair'}
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className="p-4 space-y-4 max-w-sm mx-auto border rounded-md shadow-sm">
      <h1 className="text-xl font-bold">Pair Your Scooter</h1>
      <FormError message={error || ''}/>
      <Input
        type="text"
        label="Scooter Code"
        placeholder="Enter code"
        value={vehicleCode}
        onChange={(e) => {
          setError(null);
          setVehicleCode(e.target.value);
        }}
        required
      />
      <Button onClick={handlePair} disabled={loading}>
        {loading ? 'Pairing...' : 'Pair'}
      </Button>
    </div>
  );
};
