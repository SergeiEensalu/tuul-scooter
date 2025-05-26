import React, {useEffect, useState} from 'react';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {Map} from '../../shared/ui/Map';
import {FormError} from '../../shared/ui/FormError';
import {pair} from '../../features/scooter/pair';
import {unpair} from '../../features/scooter/unpair';
import {useVehicle} from "../../shared/hooks/useVehicle";
import {parseApiError} from '../../shared/utils/parseApiError';
import {sendCommand} from '../../features/scooter/sendCommand';
import {Loader} from "../../shared/ui/Loader";

export const DashboardPage: React.FC = () => {

  const {vehicleData, loading, refresh} = useVehicle();
  const [vehicleCode, setVehicleCode] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [isPairing, setIsPairing] = useState(false);
  const [isUnpairing, setIsUnpairing] = useState(false);
  const [isSendingCommand, setIsSendingCommand] = useState<'START' | 'STOP' | null>(null);
  const [userLocation, setUserLocation] = useState<{ latitude: number; longitude: number } | null>(null);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setUserLocation({
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          });
        },
        () => {
          setUserLocation(null);
        }
      );
    }
  }, []);

  const handlePair = async () => {
    setError(null);
    setIsPairing(true);
    const result = await pair(vehicleCode);
    setIsPairing(false);

    if (!result.success) {
      setError(parseApiError(result));
      return;
    }

    await refresh();
  };

  const handleUnpair = async () => {
    if (!vehicleData?.id) return;
    setError(null);
    setIsUnpairing(true);
    const result = await unpair(vehicleData.id);
    setIsUnpairing(false);

    if (!result.success) {
      setError(parseApiError(result));
      return;
    }

    await refresh();
  };

  const handleCommand = async (cmd: 'START' | 'STOP') => {
    if (!vehicleData?.id) return;
    setError(null);
    setIsSendingCommand(cmd);
    const result = await sendCommand(vehicleData.id, cmd);
    setIsSendingCommand(null);

    if (!result.success) {
      setError(parseApiError(result));
      return;
    }

    await refresh();
  };

  const isBusy = isPairing || isUnpairing || isSendingCommand !== null;

  if (loading) {
    return <Loader/>;
  }

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
        <Map
          scooterLocation={vehicleData?.location ?? null}
          userLocation={userLocation}
        />
        <div className="flex gap-2">
          <Button onClick={() => handleCommand('START')} disabled={isBusy}>
            {isSendingCommand === 'START' ? 'Turning ON...' : 'Turn ON'}
          </Button>
          <Button onClick={() => handleCommand('STOP')} disabled={isBusy}>
            {isSendingCommand === 'STOP' ? 'Turning OFF...' : 'Turn OFF'}
          </Button>
        </div>

        <div className="pt-2">
          <Button onClick={handleUnpair} disabled={isBusy}>
            {isUnpairing ? 'Unpairing...' : 'Unpair'}
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
      <Button onClick={handlePair} disabled={isBusy}>
        {isPairing ? 'Pairing...' : 'Pair'}
      </Button>
    </div>
  );
};
