import React, {useEffect, useState} from 'react';
import {Input} from '../../shared/ui/Input';
import {Button} from '../../shared/ui/Button';
import {Map} from '../../shared/ui/Map';
import {FormError} from '../../shared/ui/FormError';
import {pair} from '../../features/scooter/pair';
import {unpair} from '../../features/scooter/unpair';
import {useVehicle} from "../../shared/hooks/useVehicle";
import {sendCommand} from '../../features/scooter/sendCommand';
import {Loader} from "../../shared/ui/Loader";
import {getReadableErrorMessage} from "../../shared/utils/getReadableErrorMessage";
import {BatteryBar} from "../../shared/ui/BatteryBar";
import {Spinner} from "../../shared/ui/Spinner";

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

  const scrollToTop = () => window.scrollTo({top: 0, behavior: 'smooth'});

  const handlePair = async () => {
    setError(null);
    setIsPairing(true);
    const result = await pair(vehicleCode);
    setIsPairing(false);

    if (!result.success) {
      setError(getReadableErrorMessage(result));
      scrollToTop()
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
      setError(getReadableErrorMessage(result));
      scrollToTop()
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
      setError(getReadableErrorMessage(result));
      scrollToTop()
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
      <div className="p-4 space-y-4 max-w-md mx-auto border rounded-xl shadow-sm">
        <div>
          <h1 className="text-2xl font-bold flex items-center gap-2">🛴 Your Scooter</h1>
          <p className="text-sm text-gray-400">ID: {vehicleData.id}</p>
        </div>

        {error && <FormError message={error}/>}

        <div className="text-sm space-y-2">

          <div className="flex items-center gap-2 py-1 rounded-md">
            <span className="text-xl">🔋</span>
            <BatteryBar level={vehicleData.soc}/>
            <span className="text-sm text-gray-600">{vehicleData.soc}%</span>
          </div>

          <div className="flex justify-between">
            <span className="font-medium text-gray-600">Location</span>
            <a
              href={`https://maps.google.com?q=${vehicleData.location?.latitude},${vehicleData.location?.longitude}`}
              target="_blank"
              rel="noopener noreferrer"
              className="text-blue-600 underline text-sm"
            >
              {vehicleData.location?.latitude.toFixed(4)}, {vehicleData.location?.longitude.toFixed(4)}
            </a>
          </div>

          <div className="flex justify-between">
            <span className="font-medium text-gray-600">Range</span>
            <span className="text-gray-900">{vehicleData.estimatedRange} km</span>
          </div>

          <div className="flex justify-between">
            <span className="font-medium text-gray-600">Powered</span>
            <span className={`font-semibold ${vehicleData.poweredOn ? 'text-green-600' : 'text-gray-400'}`}>
        {vehicleData.poweredOn ? 'ON' : 'OFF'}
      </span>
          </div>

          <div className="flex justify-between">
            <span className="font-medium text-gray-600">Odometer</span>
            <span className="text-gray-900">{vehicleData.odometer} km</span>
          </div>
        </div>
        <Map
          scooterLocation={vehicleData?.location ?? null}
          userLocation={userLocation}
        />
        <div className="flex gap-2">
          <Button onClick={() => handleCommand('START')} disabled={isBusy}>
            {isSendingCommand === 'START' ? <Spinner/> : 'Turn ON'}
          </Button>
          <Button onClick={() => handleCommand('STOP')} disabled={isBusy}>
            {isSendingCommand === 'STOP' ? <Spinner/> : 'Turn OFF'}
          </Button>
        </div>

        <div className="pt-2">
          <Button onClick={handleUnpair} disabled={isBusy}>
            {isUnpairing ? <Spinner/> : 'Unpair'}
          </Button>
        </div>
      </div>
    )
      ;
  }

  return (
    <div className="p-4 space-y-4 max-w-md mx-auto border rounded-xl shadow-sm">
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
        {isPairing ? <Spinner/> : 'Pair'}
      </Button>
    </div>
  );
};
