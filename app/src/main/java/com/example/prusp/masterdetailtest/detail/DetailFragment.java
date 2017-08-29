package com.example.prusp.masterdetailtest.detail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prusp.masterdetailtest.R;
import com.example.prusp.masterdetailtest.model.PokeType;
import com.example.prusp.masterdetailtest.model.PokeTypes;
import com.example.prusp.masterdetailtest.model.Pokemon;
import com.example.prusp.masterdetailtest.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Piotr Prus on 28.08.2017.
 */

public class DetailFragment extends MvpFragment<DetailPresenter> implements DetailView {

    public static final String ARG_ITEM_ID = "item_id";
    private static final int DECREASE_URL_BY_ONE_CHAR = 1;
    private static final String LAST_STRING_IN_URL = "type";
    private static final String DEFAULT_POKEMON_ID = "1";

    @BindView(R.id.pokemon_name_text_view)
    TextView nameTextView;
    @BindView(R.id.pokemon_height_text_view)
    TextView heightTextView;
    @BindView(R.id.pokemon_weight_text_view)
    TextView weightTextView;
    @BindView(R.id.pokemon_base_exp_text_view)
    TextView baseExpTextView;
    @BindView(R.id.poke_front_image_view)
    ImageView pokeFrontImageView;
    @BindView(R.id.poke_back_image_view)
    ImageView pokeBackImageView;
    @BindView(R.id.detail_progress_bar)
    ProgressBar detailProgressBar;
    @BindView(R.id.type_one_button)
    Button typeOneButton;
    @BindView(R.id.type_2_button)
    Button typeTwoButton;
    @BindView(R.id.pokemon_detail)
    ConstraintLayout constraintLayout;
    @BindView(R.id.error_loading_data_text_view)
    TextView errorLoadingDataTextView;

    private String typeOneId;
    private String typeTwoId;
    private TextView halfDamageToTextView;
    private TextView halfDamageFromTextView;
    private TextView doubleDamageFromTextView;
    private TextView noDamageFromTextView;
    private TextView doubleDamageToTextView;
    private TextView noDamageToTextView;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pokemon_detail, container, false);
        ButterKnife.bind(this, rootView);
        initButtonsAndDialogForTypes();
        return rootView;
    }

    private void initButtonsAndDialogForTypes() {
        typeOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadTypes(typeOneId);
                displayDialog();
            }

        });
        typeTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadTypes(typeTwoId);
                displayDialog();
            }
        });
    }

    private void displayDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(true);

        halfDamageToTextView = dialog.findViewById(R.id.half_dmg_to_value);
        halfDamageFromTextView = dialog.findViewById(R.id.half_dmg_from_value);
        noDamageToTextView = dialog.findViewById(R.id.no_dmg_to_value);
        noDamageFromTextView = dialog.findViewById(R.id.no_dmg_from_value);
        doubleDamageToTextView = dialog.findViewById(R.id.double_dmg_to_value);
        doubleDamageFromTextView = dialog.findViewById(R.id.double_dmg_from_value);
        dialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
    }

    private void initPresenter() {
        String pokemonId = DEFAULT_POKEMON_ID;
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            pokemonId = String.valueOf(getArguments().getInt(ARG_ITEM_ID));
        }
        presenter.loadData(pokemonId);
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    public void showLoading() {
        detailProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        detailProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void getDataSuccess(Pokemon.PokemonDetail detail) {
        constraintLayout.setVisibility(View.VISIBLE);
        nameTextView.setText(detail.getName());
        heightTextView.setText(String.valueOf(detail.getHeight()));
        weightTextView.setText(String.valueOf(detail.getWeight()));
        baseExpTextView.setText(String.valueOf(detail.getBaseExp()));
        Picasso.with(getContext()).load(detail.getSprites()
                .getFrontDefaultImage()).into(pokeFrontImageView);
        Picasso.with(getContext()).load(detail.getSprites()
                .getBackDefaultImage()).into(pokeBackImageView);
        ArrayList<Pokemon.Types> types = detail.getTypes();
        typeOneButton.setText(types.get(0).getPokeType().getTypeName());
        if (types.size() > 1) {
            typeTwoButton.setText(types.get(1).getPokeType().getTypeName());
            typeTwoButton.setVisibility(View.VISIBLE);
        }
        prepareTypesIdForDialog(types);
    }

    private void prepareTypesIdForDialog(ArrayList<Pokemon.Types> types) {
        String typeOneUrl = types.get(0).getPokeType().getUrl();
        typeOneId = trimTheUrlToGetTypeId(typeOneUrl);
        if (types.size() > 1) {
            String typeTwoUrl = types.get(1).getPokeType().getUrl();
            typeTwoId = trimTheUrlToGetTypeId(typeTwoUrl);
        }
    }

    private String trimTheUrlToGetTypeId(String givenUrl) {
        return givenUrl.substring(givenUrl.indexOf(LAST_STRING_IN_URL) + 5,
                givenUrl.length() - DECREASE_URL_BY_ONE_CHAR);
    }

    @Override
    public void getDataSuccess(PokeTypes pokeTypes) {
        List<PokeType> halfDamageToList = pokeTypes.getDamageRelations().getHalfDamageTo();
        concatenateTypes(halfDamageToList, halfDamageToTextView);
        List<PokeType> halfDamageFromList = pokeTypes.getDamageRelations().getHalfDamageFrom();
        concatenateTypes(halfDamageFromList, halfDamageFromTextView);
        List<PokeType> doubleDamageFromList = pokeTypes.getDamageRelations().getDoubleDamageFrom();
        concatenateTypes(doubleDamageFromList, doubleDamageFromTextView);
        List<PokeType> noDamageFromList = pokeTypes.getDamageRelations().getNoDamageFrom();
        concatenateTypes(noDamageFromList, noDamageFromTextView);
        List<PokeType> doubleDamageToList = pokeTypes.getDamageRelations().getDoubleDamageTo();
        concatenateTypes(doubleDamageToList, doubleDamageToTextView);
        List<PokeType> noDamageToList = pokeTypes.getDamageRelations().getNoDamageTo();
        concatenateTypes(noDamageToList, noDamageToTextView);
    }

    private void concatenateTypes(List<PokeType> pokeTypeList, TextView textView) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pokeTypeList.size(); i++) {
            String singleType = pokeTypeList.get(i).getTypeName();
            sb.append(singleType);
            sb.append(" ");
        }
        textView.setText(sb.toString());
    }

    @Override
    public void getDataFail(String message) {
        errorLoadingDataTextView.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), "Fail to load", Toast.LENGTH_SHORT).show();
        Log.e("DetailActivity", message);
    }

}
