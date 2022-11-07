package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding ? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

//        val activity = activity as AppCompatActivity
//        activity.setSupportActionBar(binding?.toolbar)
//        binding?.toolbar?.setNavigationOnClickListener {
//            findNavController().navigateUp()
//        }
//        binding?.character = args.character

        binding?.composeView?.setContent {
            MaterialTheme {
                Column {
                    DisplayTopAppBar(name = args.character.name)
                    DisplayImage(url = args.character.image)
                    DisplayName(name = args.character.name)
                    DisplayGender(gender = args.character.gender)
                    DisplaySpecies(species = args.character.species)
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    @Composable
    fun DisplayTopAppBar(name: String) {
        TopAppBar(
            title = {
                Text(
                    text = name,
                    color = colorResource(id = R.color.blue_light)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    findNavController().navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button",
                        tint = colorResource(id = R.color.blue_light)
                    )
                }
            },
            backgroundColor = Color.White
        )
    }

    @Composable
    fun DisplayImage(url: String) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = R.drawable.rick_and_morty_placeholder),
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    @Composable
    fun DisplayName(name: String) {
        Text(
            stringResource(id = R.string.character_name_detail, name),
            color = colorResource(id = R.color.blue_light),
            fontSize = 36.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
    }

    @Composable
    fun DisplayGender(gender: String) {
        Text(
            stringResource(id = R.string.character_gender_detail, gender),
            color = colorResource(id = R.color.green_light),
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
    }

    @Composable
    fun DisplaySpecies(species: String) {
        Text(
            stringResource(id = R.string.character_species_detail, species),
            color = colorResource(id = R.color.green_light),
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
    }

    @Preview
    @Composable
    fun DisplayPreview() {
        Column {
            DisplayTopAppBar(name = "Rick")
            DisplayImage(url = "https://example.com/image.jpg")
            DisplayName(name = "Rickasdhfgjkhgfdsadegh")
            DisplayGender(gender = "Male")
            DisplaySpecies(species = "Human")
        }
    }
}
