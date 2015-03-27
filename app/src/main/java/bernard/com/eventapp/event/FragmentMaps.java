package bernard.com.eventapp.event;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentMaps extends Fragment {
    MapView mapView;
    GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = (MapView) view.findViewById(R.id.mapp);
        mapView.onCreate(savedInstanceState);

        MapsInitializer.initialize(getActivity());

        if(mapView!=null)
        {
            googleMap = mapView.getMap();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            // Get LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

            // Create a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Get the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Get Current Location
            //Location myLocation = locationManager.getLastKnownLocation(provider);
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            // set map type
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // Get longitude and latitude of the current location
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

            // Show the current location in Google Map
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Zoom in the Google Map
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
            googleMap.addMarker(new MarkerOptions().position(latLng).title("You are here!").snippet("Now find something fun to do"));
        }

        return view;
    }

    @Override
    public void onResume()
    {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
