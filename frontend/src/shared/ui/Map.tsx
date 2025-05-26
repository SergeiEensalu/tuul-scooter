import React from 'react';
import {GoogleMap, Marker, useJsApiLoader} from '@react-google-maps/api';

type Coordinates = {
  latitude: number;
  longitude: number;
};

type Props = {
  scooterLocation?: Coordinates | null;
  userLocation?: Coordinates | null;
};

const containerStyle = {
  width: '100%',
  height: '300px',
};

const defaultCenter = {
  lat: 59.437,
  lng: 24.7536,
};

export const Map: React.FC<Props> = ({scooterLocation, userLocation}) => {
  const {isLoaded} = useJsApiLoader({
    id: 'google-map-script',
    googleMapsApiKey: import.meta.env.VITE_GOOGLE_MAPS_API_KEY,
  });

  if (!isLoaded) return <p>Loading map...</p>;

  const center =
    scooterLocation
      ? {lat: scooterLocation.latitude, lng: scooterLocation.longitude}
      : userLocation
        ? {lat: userLocation.latitude, lng: userLocation.longitude}
        : defaultCenter;

  const funnyLabels = ['🚶💨', '🦖', '😶‍🌫', '👽'];
  const label = funnyLabels[Math.floor(Math.random() * funnyLabels.length)];

  return (
    <GoogleMap
      mapContainerStyle={containerStyle}
      center={center}
      zoom={14}
    >
      {scooterLocation && (
        // Comment by Sergei: Yes, deprecated... I know! As of February 21st, 2024, google.maps.Marker is deprecated.
        <Marker
          position={{
            lat: scooterLocation.latitude,
            lng: scooterLocation.longitude,
          }}
          label="🛴"
        />
      )}
      {userLocation && (
        // Comment by Sergei: Yes, deprecated... I know! As of February 21st, 2024, google.maps.Marker is deprecated.
        <Marker
          position={{
            lat: userLocation.latitude,
            lng: userLocation.longitude,
          }}
          label={label} // well... that's definitely a prank
        />
      )}
    </GoogleMap>
  );
};
