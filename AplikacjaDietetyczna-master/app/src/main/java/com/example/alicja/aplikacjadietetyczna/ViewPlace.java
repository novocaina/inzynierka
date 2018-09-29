package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.alicja.aplikacjadietetyczna.Model.Photos;
import com.example.alicja.aplikacjadietetyczna.Model.PlaceDetails;
import com.example.alicja.aplikacjadietetyczna.Remote.IGoogleAPIService;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlace extends AppCompatActivity {
    IGoogleAPIService mService;
    PlaceDetails myPlace;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.place_open)
    TextView opening_hours;
    @BindView(R.id.place_adress)
    TextView place_address;
    @BindView(R.id.place_name)
    TextView place_name;
    Button showOnMap;
    @BindView(R.id.photo)
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        ButterKnife.bind(this);

        mService=Common.getGoogleAPIService();
        showOnMap=(Button)findViewById(R.id.btn_show_map);
        place_address.setText("");
        place_name.setText("");
        opening_hours.setText("");


        showOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(myPlace.getResult().getUrl()));
                startActivity(intent);
            }
        });
        if(Common.currentResult.getPhotos()!=null && Common.currentResult.getPhotos().length>0) {
            Picasso.with(this)
                    .load(getPhotoOfPlace(Common.currentResult.getPhotos()[0].getPhoto_reference(),1000))
                    .placeholder(R.drawable.ic_store_mall_directory_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(photo);
        }
        if(Common.currentResult.getRating()!=null && !TextUtils.isEmpty(Common.currentResult.getRating())){
        ratingBar.setRating(Float.parseFloat(Common.currentResult.getRating()));
        }
        else{
            ratingBar.setVisibility(View.GONE);
        }
        if(Common.currentResult.getOpening_hours()!=null){
            if(Common.currentResult.getOpening_hours().getOpen_now()=="true"){
            opening_hours.setText(getString(R.string.open));
            }
            else
                opening_hours.setText(getString(R.string.close));
        }
        else{
            opening_hours.setVisibility(View.GONE);
        }

        mService.getDetailPlace(getPlaceDetailUrl(Common.currentResult.getPlace_id()))
        .enqueue(new Callback<PlaceDetails>() {
            @Override
            public void onResponse(Call<PlaceDetails> call, Response<PlaceDetails> response) {
            myPlace=response.body();
            place_address.setText(myPlace.getResult().getFormatted_address());
            place_name.setText(myPlace.getResult().getName());
            }

            @Override
            public void onFailure(Call<PlaceDetails> call, Throwable t) {

            }
        });
    }

    private String getPlaceDetailUrl(String place_id) {
        StringBuilder url=new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }

    private String getPhotoOfPlace(String photo_reference,int maxWidth) {
        StringBuilder url=new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }
}
