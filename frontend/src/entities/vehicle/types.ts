export interface VehicleData {
  id: string;
  location: {
    latitude: number;
    longitude: number;
  };
  soc: number; // battery level
  odometer: number;
  poweredOn: boolean;
  estimatedRange: number;
  vehicleCode: string;
}