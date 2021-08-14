package com.number4.dogbreeds.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.number4.dogbreeds.Activity.MainActivityEvent.ButtonCloseSearchEvent;
import com.number4.dogbreeds.Activity.MainActivityEvent.ButtonSearchEvent;
import com.number4.dogbreeds.Activity.MainActivityEvent.TextSearchWatcherEvent;
import com.number4.dogbreeds.Adapter.DogAdapter;
import com.number4.dogbreeds.Fragment.FavoriteFragment;
import com.number4.dogbreeds.Fragment.MainFragment;
import com.number4.dogbreeds.Models.DogModel;
import com.number4.dogbreeds.R;
import com.number4.dogbreeds.ViewModels.DogViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG= "TAG";
    public  static List<DogModel> dogModels = new ArrayList<>();
    private Fragment mainFragment, favoriteFragment;
    public static DogViewModel dogViewModel;
    private  ImageView imageSearch;
    private LinearLayout linearLayoutSearchBar;
    private ImageView btnCloseSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        generateData();
        dogViewModel = ViewModelProviders.of(this).get(DogViewModel.class);
        for (DogModel dog : MainActivity.dogModels
        ) {
            dogViewModel.insert(dog);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
        setupView();

    }
    public  static  void UpdateData(DogModel dogModel){
        MainActivity.dogViewModel.update(dogModel);
    }
    private void setupView(){
        MainFragment mainFragment = MainFragment.newInstance("some");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,mainFragment,TAG)
                .commit();
        mainFragment.setCallback(new MainFragment.Callback() {
            @Override
            public void doSomeThing() {
                MainActivity.this.doSomeThingInActivity();
            }
        });
        findViewById(R.id.imageSearch).setOnClickListener(new ButtonSearchEvent());
        btnCloseSearch = findViewById(R.id.btnCloseSearch);
        btnCloseSearch.setOnClickListener(new ButtonCloseSearchEvent(this));
        ((EditText)findViewById(R.id.txtSearch)).addTextChangedListener(new TextSearchWatcherEvent(this));


        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        imageSearch = findViewById(R.id.imageSearch);
        linearLayoutSearchBar = findViewById(R.id.searchBar);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            linearLayoutSearchBar.setVisibility(View.VISIBLE);

                            imageSearch.setVisibility(View.VISIBLE);
                            Log.d("navigation_home", "home");
                            if(mainFragment == null)
                                mainFragment = new MainFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                    mainFragment).commit();
                            break;
                        case R.id.navigation_favorite:
                            linearLayoutSearchBar.setVisibility(View.GONE);
                            btnCloseSearch.performClick();
                            imageSearch.setVisibility(View.GONE);
                            if(favoriteFragment == null)
                                favoriteFragment = new FavoriteFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                    favoriteFragment).commit();
                            Log.d("navigation_favorite", "home");
                            break;
                    }

                    return true;
                }
            };
    public void doSomeThingInActivity(){
        Log.e(TAG,"You clicked");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public static List<DogModel> generateData(){
        dogModels.add(new DogModel("Akita Inu",R.drawable.akita,"0,6 - 0,7m","23 - 39kg","miền Bắc Nhật Bản", "12 - 15 năm", "Nhật Bản ","Chó Nhật Akita có vóc dáng khá to lớn. Chúng có vẻ ngoài oai vệ, mạnh mẽ, phần đầu khá to, hình tam giác gần giống với chó sói, tương phản với đôi mắt nhỏ. Chúng có mõm ngắn và đôi tai nhọn, mắt đen tuyền. \n" +
                "Chó Akita có dáng đứng hiên ngang, vững vàng. Lưng dài hơn chiều cao, ngực rộng, cổ dày và cơ bắp. Đôi chân Akita dài, khá thon thả. Đặc biệt, phần ngón chân có màng để bơi gần giống chân mèo. \n" +
                "Akita  nguyên bản thường có màu trắng, nâu, nâu vàng, đỏ hoặc đốm. Màu càng sáng thì càng đẹp, trong đó chó Akita trắng được nhiều người yêu thích. Còn Akita Mỹ có nhiều màu đa dạng hơn. \n" +
                " Mũi Akita thường có màu nâu hoặc đen (trong đó màu đen thường được yêu thích hơn). Một đặc điểm không thể bỏ qua của Akita là cái đuôi dài, mượt mà, uốn cong trên lưng.\n" +
                "Trên đây là những đặc điểm hình dáng bên ngoài của một chú chó Akita thuần chủng. Tuy nhiên hiện nay, số lượng chó Akita 100% thuần chủng khá hiếm, không có nhiều. Khi lựa chó Akita, bạn phải thật sự tỉnh táo để không trở thành “con mồi” của những địa chỉ lừa đảo.\n" +
                "Akita là một “người bảo vệ” trung thành, dũng cảm nhưng cũng không kém phần bướng bỉnh. Chúng rất nghĩa tình với chủ nhân, luôn coi nhiệm vụ tối thượng là phục vụ và làm hài lòng chủ nhân. Lòng quyết tâm có sẵn là vũ khí lợi hại khi đối mặt với kẻ thù. Chúng can đảm, không sợ hãi hay dễ dàng đầu hàng trước những thách thức.\n" +
                "Giống chó Akita hay đề phòng trước người lạ. Tư thế đứng của chúng như một rào cản đối với hầu hết những ai có ý định làm hại hay gây rắc rối. Loài chó này rất độc lập, chúng có thể không tuân lệnh nếu cảm thấy vô lý.",
                "akita_inu_360_720_50_"));
        dogModels.add(new DogModel("Bernese Mountain Dog",R.drawable.bernese_mountain,"0,6 - 0,7m","39 - 50kg","Thụy Sĩ", "6-8 năm", "Thụy Sĩ","Với thân hình cao lớn, bộ lông mềm mượt, đặc biệt nhất là 3 màu lông không thể lẫn vào đâu được, Bernese Mountain Dog mang nét đáng yêu mà vẫn oai phong, dũng mãnh. Hiếm ai biết được những anh bạn này có nguồn gốc từ chó sói. \n" +
                "Chó núi tam sắc còn được gọi là chó núi Bernese (Bernese Mountain Dog) hay Sennenhund Berner (ở Đức và Thụy Sĩ ) là một trong những giống chó lớn trên thế giới. Cái tên Sennenhund được ghép từ từ “Senne” (nghĩa là “núi cao đồng cỏ” trong tiếng Đức) và từ “Hund” (chó săn). Berner hoặc Bernese trong tiếng Anh là từ dùng để chỉ loại chó trang trại (nuôi để bảo vệ và kéo xe). \n" +
                "Bernese Mountain Dog là một trong những giống chó khổng lồ trên thế giới. Chúng thuộc giống chó kéo xe của vùng núi Bernese nay thuộc Thụy Sỹ. Giống chó này có ngoại hình khủng, với cân nặng khoảng 40 đến 50kg và cao trung bình 55- 65cm. Bernese Mountain Dog được coi là hậu duệ của những giống chó sói.\n" +
                "Giống chó đến từ vùng núi Bernese này khá trầm tính, chúng không gây ổn ào và rất ngoan ngoãn.. Mặc dù là hậu duệ của chó sói nhưng Bernese Mountain Dog là rất hiền và quấn quýt chủ. Ưu điểm của giống chó này là sự chủ động và vâng lời. Bernese Mountain được coi là một giống chó hạnh phúc được nuôi nhiều trong các hộ gia đình hiện nay.\n" +
                "Bernese dễ sống và phù hợp với nhiều môi trường sống khác nhau. Chúng dễ hoàn nhập và chung sống được với các giống vật nuôi khác. Hiện nay, tại Việt Nam giá của giống chó này khoảng 10 đến 15 triệu đồng một chú chó thuần chủng, đủ giấy tờ.\n" +
                "Tự chủ, thận trọng, tập trung, dũng cảm, quấn quýt và trung thành, những tính cách tuyệt vời ấy tồn tại một cách tự nhiên trong Bernese Mountain Dog. Giúp chúng lấy lòng và tạo nên mối liên hệ khăng khít với chủ nhân. Không chỉ có vậy, những chú chó này còn cực kỳ điềm tĩnh với người lạ, dễ bảo và rất ngoan ngoãn.",
                "bernese_mountain_dog_360_720_50_"));
        dogModels.add(new DogModel("Affenpinscher",R.drawable.affenpinscher,"0,2 - 0,3m","3 - 6kg","Đức", "12 - 15 năm", "Chưa rõ ","Affenpinscher, còn được gọi là \"Monkey Dog\" (\"affen\" có nghĩa là \"vượn / khỉ\" trong tiếng Đức, và \"pincher \"có nghĩa là\" chó săn \"), nhỏ nhưng hung dữ, tràn đầy sức sống và năng lượng. Con quỷ nhỏ có râu ria mép này là hậu duệ của vô số loài chó sục nhỏ sống trong các chuồng và cửa hàng thế kỷ 17 và 18 trên khắp châu Âu, xua đuổi chúng khỏi chuột và chuột.\n" +
                "\n" +
                "Một số loài chó thông minh, có lông xù chắc hẳn đã lọt vào mắt xanh của các quý cô, bởi vì cuối cùng chúng đã được lai tạo để càng nhỏ, càng tốt để làm bạn đồng hành. Ngày nay, Affen là một giống chó quý hiếm, nhưng nó đã được công chúng chú ý vào năm 2002 khi Super Nova của Ch Yarrow giành giải Nhóm đồ chơi vào năm 2002 tại Triển lãm Câu lạc bộ Westminster Kennel được truyền hình toàn quốc.\n" +
                "\n" +
                "Giống chó này kết hợp sự quyến rũ của chú hề với bản tính táo bạo. Nó học hỏi nhanh chóng và dễ dàng điều chỉnh để thay đổi. Mạnh mẽ, thông minh và lanh lợi, Affenpinscher là một con chó canh gác xuất sắc không sợ gì cả. Nó bình thường không ồn ào, nhưng rất dễ bị kích động. Nó nghiêm túc thực hiện nhiệm vụ canh gác nhà cửa, gia đình và lãnh thổ, và sẽ không ngần ngại cảnh báo toàn bộ khu phố rằng ai đó đang đến gần cửa trước. Thường xuyên giao lưu với những người và động vật khác là điều bắt buộc để Affenpinscher trưởng thành cân đối.\n" +
                "\n" +
                "Affenpinscher có đầu óc riêng và thường bị cho là cứng đầu. Nó cần được đào tạo sớm và nhất quán." +
                "Mặc dù Affenpinschers được biết đến với việc làm cho mọi người cười, nhưng chúng không phải là giống chó tốt nhất cho một hộ gia đình đầy trẻ em mặc dù có những trò hề. Chúng không được biết là đặc biệt thích trẻ em và chúng sẽ không ngần ngại cắn nếu bị khiêu khích.\n" +
                "\n" +
                "Hãy cân nhắc nó nếu bạn quan tâm đến một chú chó nhỏ thích ngắm cảnh, là một chú chó canh gác xuất sắc và sẽ luôn khiến bạn cười.",""));
        dogModels.add(new DogModel("Cane corso",R.drawable.cane_corso,"0,3m","40 - 54kg","Chó chiến La Mã","10 -12 năm","Ý","Cane Corso (viết tắt là Corso) là một giống chó nghiêm túc dành cho những người nghiêm túc muốn có một con chó làm bạn đồng hành và người có thể cung cấp cho anh ta sự hướng dẫn vững chắc và yêu thương mà anh ta cần để trở thành một chú chó tuyệt vời. Nó là một con chó chỉ dành cho gia đình. Đừng mong đợi nó kết thân với mọi người mà nó gặp. Nó không quan tâm đến những người hoặc động vật khác bên ngoài gia đình mình, nhưng những người trong gia đình sẽ có lòng trung thành và sự bảo vệ không thể tách rời của nó.\n" +
                "\n" +
                "Nó không muốn chỉ nằm lòng cả ngày và sẽ tìm “công việc” của riêng mình để làm nếu bạn không cung cấp: thường chạy hàng rào và sủa người qua đường, đào hố sang Trung Quốc hoặc nhai đồ đạc của bạn. Nếu bạn có một trang trại hoặc trại chăn nuôi, nó sẽ giúp bạn chăn nuôi; nếu không, hãy cho nó tham gia vào một môn thể thao dành cho chó như chạy đua, lặn ở bến tàu hoặc theo dõi.",""));
        dogModels.add(new DogModel("Chugs",R.drawable.chugs,"0,25 - 0,35m","4,5 - 9kg","lai giữa Chihuahua và Pug","10 - 13năm","Hoa Kì","Chúng có thể dễ bị tăng cân quá mức nếu cho ăn quá nhiều. Đảm bảo tuân thủ một chế độ ăn uống và lịch trình cho ăn phù hợp.\n" +
                "Chúng có xu hướng \"thích thú\", điều này có thể giúp chúng trở thành những con chó canh gác tốt. Tuy nhiên, với việc huấn luyện sớm, bạn có thể hạn chế tiếng sủa không mong muốn.\n" +
                "Hầu hết các chủ sở hữu cho biết chó của họ có tính cách ngớ ngẩn và thích hành động ngớ ngẩn. Chúng sẽ rất tuyệt trong việc khiến bạn mỉm cười." +
                "Là một con chó hầu như chỉ có lông não (mõm ngắn), Chúng có thể dễ bị say nắng. Chăm sóc chúng thêm khi thời tiết nóng.\n" +
                "Chúng cũng có bộ lông ngắn hơn, vì vậy chúng có thể cần được bảo vệ thêm bằng áo khoác khi thời tiết lạnh." +
                "Màu sắc chính của chúng là nâu, đen, nâu vàng, kem và trắng.",""));
        dogModels.add(new DogModel("Chi Chi",R.drawable.chi_chi,"0,15 - 0,3m","2 - 5kg","lai giữa Chihuahua & Chinese Crested","11 - 20 năm","Bắc Mỹ","Chi Chi là một giống chó lai. Chúng không phải là giống chó thuần chủng như bố mẹ Chihuahua và Chinese Crested.\n" +
                "Màu sắc chính của Chi Chi là nâu, đen, nâu vàng, kem và trắng. Đôi khi áo khoác của chúng chắc chắn, và đôi khi chúng có sự pha trộn của nhiều màu sắc.\n" +
                "Chúng thường gần như không có lông ngoại trừ một vài mảng, đó là lý do tại sao chúng thường là lựa chọn tốt cho những người bị dị ứng.\n" +
                "Chi Chi có mức năng lượng cao. Đảm bảo rằng Chi Chi của bạn được đi bộ ít nhất từ nửa tiếng đến một tiếng mỗi ngày với một vài buổi vui chơi vận động tốt và kết hợp đi bộ ngắn hơn.\n" +
                "Vì Chi Chi là một con chó nhỏ, chúng có thể dễ dàng bị thương bởi những đứa trẻ quá phấn khích. Chi Chi chủ yếu thích ở xung quanh người lớn hoặc trẻ lớn hơn biết cách chơi nhẹ nhàng.\n" +
                "Khi nói đến những vật nuôi khác, Chi Chi hoàn toàn có khả năng hòa đồng với những con vật khác." +
                "Khi nói đến việc huấn luyện, những con chó này phù hợp nhất với những người đã có kinh nghiệm nuôi chó trước đó.",""));
        dogModels.add(new DogModel("Cockapoo",R.drawable.dogue_de_bordeaux,"0,25 - 0,4m","2,5 -8,5kg","lai giữa Cocker Spaniel và Poodle","12 -15 năm","Mỹ","Cockapoo không được biết đến là một loài hay sủa. Tuy nhiên, một số sẽ sủa khi thấy ai đó đến gần nhà hoặc khi họ ở một mình trong thời gian dài.\n" +
                "Cockapoo phải là giống chó không lông, có ít mùi hôi ở da và lông. Anh ấy yêu cầu chải đầu hàng ngày và thỉnh thoảng sẽ cần cắt tỉa và vuốt tóc.\n" +
                "Cockapoos thường tốt cho những người bị dị ứng vì chúng tạo ra ít lông và lông.\n" +
                "Cockapoos được phát triển để trở thành những con chó đồng hành và thường thân thiện và cực kỳ vui vẻ. Chúng có thể làm tốt với những con chó, vật nuôi và trẻ em khác; Tuy nhiên, trẻ lớn hơn, chu đáo hơn thường là tốt nhất.\n" +
                "Mặc dù Cockapoo Standard hoặc Maxi Cockapoo không dễ dàng thích nghi với cuộc sống căn hộ như những người bạn nhỏ hơn của nó, nó có thể làm tốt nếu được tập luyện đúng cách . Tình huống lý tưởng là một ngôi nhà có sân nhỏ, có hàng rào.\n" +
                "Cockapoo rất thông minh nên nó rất dễ huấn luyện với sự củng cố tích cực.\n" +
                "Cockapoos có mức năng lượng vừa phải nhưng vẫn cần tập thể dục hàng ngày. Hãy dành cho anh ấy ít nhất 15 phút mỗi ngày và thực hiện nhiều hoạt động khác nhau, chẳng hạn như trò chơi chạy nhanh, đi bộ và chạy tốt.\n",""));
        dogModels.add(new DogModel("Cavachon",R.drawable.cavachon,"~0,3m","6,8 - 9kg","lai giữa King Charles Spaniel & Bichon Frise","10 -15 năm","Bắc Mĩ","Cavachons là giống chó lai. Chúng không phải là những con thuần chủng như bố mẹ Cavalier King Charles Spaniel hoặc Bichon Frise.\n" +
                "Màu sắc chủ đạo của Cavachons là kem, trắng và xanh. Rất ít áo khoác chắc chắn, và chúng hầu như luôn có sự kết hợp của nhiều màu sắc.\n" +
                "Cavachons thường có áo khoác dài vừa phải, và chúng thường được coi là lựa chọn tốt cho những người bị dị ứng. Có những loại Cavachons được bọc lâu hơn, mặc dù chúng có thể không thân thiện với dị ứng.\n" +
                "Cavachons khá dễ chải chuốt. Đánh răng tốt 3-4 lần một tuần là bí quyết.\n" +
                "Hầu hết các Cavachons đều hòa đồng với trẻ em và thích chơi đùa. Tuy nhiên, như với tất cả các con chó, thời gian chơi nên được giám sát.\n" +
                "Cavachon hòa đồng và thích bầu bạn với những con chó khác, miễn là chúng nhận được sự quan tâm công bằng từ chủ nhân.",""));
        dogModels.add(new DogModel("Collie",R.drawable.collie,"0,5 - 0,7m","22 - 32kg","Scotland","10 - 14 năm","","Collie thường im lặng trừ khi nó có lý do để sủa. Tuy nhiên, nếu ở một mình quá thường xuyên hoặc nếu thấy buồn chán, chúng sẽ sủa quá mức .\n" +
                "Cả hai giống đều cần chải lông, nhưng Rough Collie đặc biệt cần chải lông thường xuyên để giữ bộ lông sạch sẽ và không bị rối.\n" +
                "Nhiều chú chó Collies nhạy cảm với các loại thuốc bao gồm ivermectin, loại thuốc được sử dụng trong điều trị giun tim . Hãy nói chuyện với bác sĩ thú y trước khi cho Collie uống thuốc ngừa giun tim hoặc bất kỳ loại thuốc nào khác.\n" +
                "Hãy cẩn thận từ người mà bạn có được một chú chó Collie. Sự nổi tiếng của Collie đã khiến những nhà lai tạo không có đạo đức hành động mà không quan tâm đến tính khí, sức khỏe hay hình dạng. Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng. Hãy tìm một nhà lai tạo có uy tín , người đã kiểm tra những con chó giống của cô ấy để đảm bảo rằng chúng không mắc các bệnh di truyền có thể truyền sang chó con và chúng có tính khí tốt.",""));
        dogModels.add(new DogModel("Chi poo",R.drawable.chi_poo,"0,15 - 0,4m","2,3 - 9kg","lai giữa Chihuahua và Toy hoặc Teacup Poodle","12 - 15 năm","Mỹ","Chi-Poos là giống chó lai tạp. Chúng không phải là những con thuần chủng như Chihuahua hoặc Poodle bố mẹ của chúng.\n" +
                "Màu sắc chính của Chi-Poos là; kem, nâu, xanh lam, vện, bạc, xám, nâu vàng, trắng và đen. Đôi khi áo khoác của chúng chắc chắn, và đôi khi chúng có sự pha trộn của nhiều màu sắc.\n" +
                "Những con chó này thường có bộ lông dài trung bình và chúng thường được coi là lựa chọn tốt cho những người bị dị ứng. Có những Chi-Po được tráng dài hơn, cũng như những Chi-Po được tráng ngắn hơn.\n" +
                "Chi-Poos có thể thích nghi với mọi hoàn cảnh sống. Căn hộ hoặc nhà lớn, người độc thân hoặc đại gia đình, họ sẽ phù hợp ở bất cứ đâu.\n" +
                "Chi-Poos không đặc biệt thích hợp với thời tiết khắc nghiệt. Chúng xử lý nhiệt tốt hơn lạnh, nhưng bạn có thể cần thoa kem chống nắng doggy vào mùa hè.\n" +
                "Vì Chi-Poo là một con chó nhỏ, chúng có thể dễ dàng bị thương bởi những đứa trẻ quá phấn khích. Chi-Poos chủ yếu thích ở xung quanh người lớn hoặc trẻ lớn hơn biết cách chơi nhẹ nhàng.",""));
        dogModels.add(new DogModel("Dachsador",R.drawable.dachsador,"0,4 - 0,6m","13,5 - 18kg","lai giữa Dachshund & Labrador ","12 - 14 năm","","Dachsador là một giống chó hỗn hợp. Chúng không phải là thuần chủng như bố mẹ Dachshund hoặc Labrador Retriever.\n" +
                "Các màu phổ biến nhất của Dachsador là nâu sô cô la, đen và vàng.\n" +
                "Dachsador là một con chó năng lượng cao sẽ cần ít nhất hai lần đi bộ mỗi ngày, lý tưởng là từ 45 phút đến một giờ.\n" +
                "Khi nói đến việc chải lông, một lần chải lông nhanh 10 phút mỗi ngày là đủ để giữ cho bộ lông của chó luôn ở trạng thái tốt nhất.\n" +
                "Chó Dachsador và trẻ em rất phù hợp với nhau. Giống chó lai này rất thân thiện và vui tươi và sẽ thích tham gia các buổi vui chơi cùng lũ trẻ. Thời gian chơi vẫn phải luôn được giám sát.\n" +
                "Loài chó này thông minh, vì vậy hãy đảm bảo cung cấp đồ chơi tương tác để chúng luôn cảnh giác và tò mò.",""));
        dogModels.add(new DogModel("Dachshund",R.drawable.dachshund,"~0,2m","7,2 - 14,5kg","Đức","12 - 15 năm","","Dachshunds có thể cứng đầu và khó phá nhà. Khuyến khích đào tạo trong thùng .\n" +
                "Dachshunds là giống chó thông minh với bản tính độc lập và tinh thần vui tươi. Vì điều này, chúng có thể nghịch ngợm. Hãy kiên nhẫn, chắc chắn và nhất quán khi huấn luyện chúng.\n" +
                "Bởi vì chúng được lai tạo để săn bắn, chúng có thể thể hiện một số hành vi liên quan đến điều đó. Chúng được thiết kế để đào hang của lửng, và bản năng đó có thể khiến chúng đào sâu dahlias của bạn. Chúng được lai tạo để trở nên ngoan cường khi đi săn, và bản năng này có thể khiến chúng không ngừng quấy rầy bạn để được đãi ngộ. Chúng được lai tạo để không chỉ săn bắn mà còn giết con mồi; trong hộ gia đình bạn, \"con mồi\" rất có thể sẽ là đồ chơi của Dachshund và nó sẽ \"giết\" chúng một cách hiệu quả.\n" +
                "Chó săn có những tiếng sủa to và sâu đối với một con chó có kích thước như chúng - và chúng tôi thích sủa !\n" +
                "Nếu bạn không chú ý, chú chó Dachshund của bạn có thể trở nên béo và lười biếng, điều này sẽ khiến tấm lưng mỏng manh của nó thêm căng thẳng. Đảm bảo theo dõi lượng thức ăn của Dachshund và giữ cho nó ở mức cân nặng hợp lý.\n" +
                "Dachshunds dễ bị trượt đĩa ở lưng, có thể dẫn đến tê liệt một phần hoặc toàn bộ. Đừng để chúng nhảy từ nơi cao xuống, và khi bạn giữ chúng, hãy đỡ lưng chúng.\n" +
                "Dachshund của bạn có thể sẽ là một con chó một người. Về bản chất, anh ta có thể nghi ngờ người lạ, vì vậy điều quan trọng là phải giao tiếp xã hội với anh ta khi anh ta còn là một chú cún con .\n" +
                "Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng.",""));
        dogModels.add(new DogModel("Dalmatian",R.drawable.dalmatian,"0,5 - 0,6m","","","","","Chó đốm cần tập thể dục hàng ngày nếu không chúng sẽ trở nên buồn chán và phá phách .\n" +
                "Chó đốm rụng rời! Chải lông thường xuyên và kỹ lưỡng có thể giúp kiểm soát rụng lông nhưng chó đốm sẽ rụng lông.\n" +
                "Chó đốm cần được huấn luyện để giúp chúng trở thành thành viên tốt trong gia đình. Họ có thể cứng đầu, vì vậy nếu không có sự huấn luyện kiên định và chắc chắn, bạn có thể kết thúc với một người lớn không thể quản lý được.\n" +
                "Việc xã hội hóa sớm với những con chó, mèo và vật nuôi nhỏ khác, trẻ em và người lớn là điều bắt buộc.\n" +
                "Chó đốm không thích ở lâu một mình. Họ làm tốt nhất khi họ có thể được tham gia vào tất cả các hoạt động gia đình và ngủ và sống ở nơi gia đình nhân loại của họ.\n" +
                "Các gia đình có con nhỏ cần lưu ý rằng chó đốm là loài chó rất háo sắc và hiếu động và có thể vô tình làm ngã trẻ nhỏ.\n" +
                "Các bộ phim hoạt hình và người thật \"101 chú chó đốm\", cả hai đều của Disney, đã gây ra sự nổi tiếng của giống chó này. Những người vô lương tâm tìm cách kiếm tiền trong thời kỳ bùng nổ đã nuôi chó đốm một cách bừa bãi, không chú ý đến sức khỏe hay tính khí. Hãy là người tiêu dùng thận trọng và có hiểu biết khi tìm kiếm chú chó đốm của bạn.",""));
        dogModels.add(new DogModel("Doberman Pinschers",R.drawable.doberman,"0,6 - 0,7m","27 - 36kg","Lai giữa Rottweiler, German Pinscher, và Black and Tan Terrie","10 - 13 năm","","Doberman có rất nhiều năng lượng và cần vận động nhiều .\n" +
                "Giống chó này có thể bảo vệ, vì vậy đừng ngạc nhiên khi chúng đảm nhận vai trò giám hộ trong nhà.\n" +
                "Dobie sẽ đảm nhận vai trò alpha trong gia đình của bạn nếu bạn không phải là một nhà lãnh đạo mạnh mẽ. Đào tạo sớm và nhất quán là rất quan trọng để thiết lập vai trò của bạn với tư cách là người lãnh đạo nhóm.\n" +
                "Dobie nhạy cảm với thời tiết lạnh và cần có nơi trú ẩn thích hợp vào mùa đông (chúng thích ở trong nhà cạnh lò sưởi).\n" +
                "Doberman Pinscher là một giống chó gia đình và không nên để một mình. Chúng phát triển mạnh khi được tham gia vào các hoạt động gia đình.\n" +
                "Doberman đã nổi tiếng là xấu xa. Mặc dù Doberman của bạn có thể có tính cách ngọt ngào nhưng hàng xóm và người lạ có thể sợ chúng.\n" +
                "Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng.",""));
        dogModels.add(new DogModel("Drever",R.drawable.drever,"0,3 -0,4m","16 -18kg","Thụy Điển","12 - 15 năm","","Màu sắc của bộ lông của Drever chủ yếu là vện, nâu vàng, đỏ và ba màu. Chúng cũng có thể có đánh dấu bằng màu sable hoặc màu trắng. Áo khoác của họ rất dễ chải chuốt. Đánh răng tốt mỗi tuần có thể sẽ làm được.\n" +
                "Những người thợ lặn được lai tạo để trở thành những kẻ theo dõi những kẻ đi săn nhưng rất tình cảm với chủ của chúng và thích âu yếm nếu chúng ở trong nhà.\n" +
                "Drever có ý chí mạnh mẽ và cần một huấn luyện viên vững vàng. Chúng có thể trở thành những con chó canh gác tốt và sẽ sủa khi có dấu hiệu đầu tiên khi chúng cảm thấy có rắc rối.\n" +
                "Người lặn có mức năng lượng cao. Đảm bảo chó của bạn được đi bộ ít nhất từ nửa giờ đến kéo dài một giờ mỗi ngày với một vài buổi vui chơi vận động tốt và kết hợp đi bộ ngắn hơn.\n" +
                "Những người thợ lặn tự nhiên không thích các loài động vật khác và có thể thích làm thú cưng một mình trong nhà. Tuy nhiên, nhiều Drever rất hòa thuận với những con chó khác, vì vậy nó thực sự phụ thuộc vào việc huấn luyện, xã hội hóa và sự may mắn khi rút thăm.\n" +
                "Hầu hết các Drever đều thích trẻ em nhưng có thể thích những đứa trẻ lớn hơn biết cách tương tác đúng cách và nhẹ nhàng với vật nuôi.",""));
        dogModels.add(new DogModel("Giant Schnauzer",R.drawable.giant_schnauzer,"0,6 - 0,7m","25 - 36kg","Đức","10 - 12 năm","Đức","Giant Schnauzers là giống chó năng động và cần ít nhất hai lần đi bộ dài mỗi ngày hoặc 30 đến 60 phút tập thể dục mạnh mẽ ở sân sau.\n" +
                "Nếu không được tập thể dục và kích thích tinh thần thích hợp, Giant Schnauzers có thể trở nên rất phá phách và khó xử lý.\n" +
                "Giant Schnauzers không được khuyến khích cho những người lần đầu tiên sở hữu hoặc nhút nhát. Họ cần một nhà lãnh đạo mạnh mẽ, người có thể đưa ra các quy tắc rõ ràng và nhất quán mà không cần dùng đến vũ lực.\n" +
                "Giant Schnauzers sẽ trở thành những con chó bảo vệ xuất sắc.\n" +
                "Căn hộ không phải là nơi ở thích hợp cho Giant Schnauzers. Chúng cần một sân rộng có hàng rào để chúng có thể chơi và chạy một cách an toàn.\n" +
                "Xã hội hóa là điều bắt buộc với giống chó này. Chúng có thể hung dữ với người, chó và những động vật khác mà chúng không biết. Bản chất chúng nghi ngờ người lạ và cần phải làm quen với việc trải nghiệm những con người và tình huống mới.\n" +
                "Giant Schnauzers yêu cầu đánh răng từ một đến ba lần một tuần. Lông của chúng cũng phải được tước hoặc cắt bớt để trông gọn gàng.\n" +
                "Giant Schnauzers là những con chó thông minh, học hỏi nhanh và xuất sắc trong nhiều công việc. Hãy kiên định và nhất quán, đồng thời sử dụng các kỹ thuật củng cố tích cực như khen ngợi, vui chơi và thưởng thức ăn. Giant Schnauzers sẽ nhìn thấy và lợi dụng bất kỳ sự mâu thuẫn nào trong hành vi của bạn.",""));
        dogModels.add(new DogModel("Goldador",R.drawable.goldador,"0,5 - 0,6m","27 -36kg","lai giữa Labrador Retriever & Golden Retriever","10 -15 năm","Anh","Goldadors thích ăn uống và có thể dễ bị thừa cân nếu không tập thể dục đầy đủ.\n" +
                "Goldadors là những con chó gia đình tốt và thường làm tốt với trẻ em ở mọi lứa tuổi.\n" +
                "Goldador rụng lông vừa phải và cần đánh răng hàng tuần.\n" +
                "Goldadors thường hòa thuận với những con chó và vật nuôi khác, đặc biệt là khi chúng được nuôi cùng hoặc hòa nhập với chúng khi còn nhỏ.\n" +
                "Goldadors yêu cầu tập thể dục khoảng 30 phút mỗi ngày. Chúng thích được ở ngoài trời và có thể trở thành những người bạn đồng hành chạy bộ tuyệt vời.\n" +
                "Mặc dù một ngôi nhà có sân có hàng rào là ngôi nhà lý tưởng cho Goldador, nhưng chúng có thể làm tốt trong một căn hộ hoặc chung cư với các bài tập thể dục thích hợp.\n" +
                "Yêu thương, tận tụy và tràn đầy năng lượng, những chú chó lai Goldador được đánh giá cao vì khả năng huấn luyện tốt. Là con lai giữa Golden Retriever và Labrador Retriever, sự pha trộn này thừa hưởng một số đặc điểm tốt nhất từ cả bố và mẹ thuần chủng",""));
        dogModels.add(new DogModel("Golden Retriever",R.drawable.golden_retriever,"0,5 - 0,6m","25 - 34kg","Lord Tweedmouth, Scotland","10 - 12 năm","Mỹ","Golden Retrievers rụng nhiều, đặc biệt là vào mùa xuân và mùa thu. Chải lông hàng ngày sẽ giúp loại bỏ một số lông tơ trên bộ lông, giúp lông không bám vào quần áo và khắp nhà. Nhưng nếu bạn sống với Golden, bạn sẽ phải làm quen với lông chó.\n" +
                "Golden Retrievers là giống chó gia đình; chúng cần sống trong nhà với \"bầy\" con người của chúng, và không nên ở một mình hàng giờ ở sân sau.\n" +
                "Golden Retrievers là giống chó năng động, cần tập thể dục chăm chỉ 40-60 phút mỗi ngày . Chúng phát triển mạnh nhờ huấn luyện vâng lời, các lớp học nhanh nhẹn và các hoạt động khác của chó, đây là một cách tuyệt vời để giúp chó của bạn tập thể dục thể chất và tinh thần.\n" +
                "Mặc dù chúng hiền lành và đáng tin cậy với trẻ em, nhưng Golden Retrievers là những con chó to lớn, hống hách có thể vô tình xô ngã trẻ nhỏ.\n" +
                "Goldens thích ăn, và sẽ nhanh chóng bị thừa cân nếu ăn quá nhiều. Hạn chế đồ ăn vặt, đo lượng thức ăn hàng ngày của con chó và cho nó ăn trong các bữa ăn thông thường thay vì để thức ăn liên tục.\n" +
                "Bởi vì Golden Retriever rất phổ biến, có nhiều người nhân giống Goldens quan tâm đến việc kiếm tiền từ nhu cầu về chó con hơn là việc nhân giống những con chó khỏe mạnh hạnh phúc. Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng. Hãy tìm một nhà lai tạo có uy tín , người đã kiểm tra những con chó giống của cô ấy để đảm bảo rằng chúng không mắc các bệnh di truyền có thể truyền sang chó con và chúng có tính khí tốt.\n" +
                "Golden Retriever là một trong những giống chó phổ biến nhất ở Hoa Kỳ. Thái độ thân thiện, khoan dung của giống chó này khiến chúng trở thành vật nuôi tuyệt vời trong gia đình và trí thông minh của chúng khiến chúng trở thành những con chó có khả năng làm việc cao",""));
        dogModels.add(new DogModel("Goldendoodle",R.drawable.goldendoodle,"0,5 - 0,6m","22 - 40kg","lai giữa Golden Retriever & Poodle","10 - 15 năm","Bắc Mỹ và Úc","Chó thiết kế, còn được gọi là chó lai, không phải là giống thật - chúng là con lai của hai giống cụ thể. Nếu bạn quan tâm đến một con chó con Goldendoodle, hãy hiểu rằng ngoại hình, kích thước và tính khí của nó không thể đoán được như những con chó thuần chủng, vì bạn không biết những đặc điểm nào từ mỗi giống sẽ thể hiện ở bất kỳ con chó nhất định nào.\n" +
                "Goldendoodle là kết quả của quá trình lai tạo từ Poodle đến Golden Retriever . Cho đến nay có rất ít giống lai đa thế hệ (lai giữa hai Goldendoodles).\n" +
                "Goldendoodle được coi là giống chó không rụng lông, nhưng nó đòi hỏi phải chải chuốt và cắt tỉa lông thường xuyên. Nếu bộ lông ngắn, nên cắt tỉa lông từ sáu đến tám tuần một lần và chải lông vài tuần một lần . Nếu bộ lông được giữ ở trạng thái tự nhiên, nó nên được chải một hoặc hai tuần một lần.\n" +
                "Goldendoodle không phải là một con chó canh gác, và nó thường không ồn ào. Nó có thể không sủa ngay cả khi ai đó gõ cửa.\n" +
                "Mặc dù anh ta có mức năng lượng trung bình, nhưng Goldendoodle không được khuyến khích cho các căn hộ. Anh ấy làm tốt hơn nhiều trong một ngôi nhà có hàng rào sân.\n" +
                "Goldendoodle yêu cầu khoảng 20 đến 30 phút tập thể dục hàng ngày .\n" +
                "Là một người bạn đồng hành tuyệt vời trong gia đình, Goldendoodle thường hòa thuận với trẻ em và đối xử tốt với những con chó khác và vật nuôi trong gia đình.\n" +
                "Goldendoodle là một con chó rất xã hội, không nên sống xa gia đình. Chúng không thích hợp để sống trong cũi hoặc bên ngoài; chúng muốn ở trong nhà.\n" +
                "Goldendoodle có thể bị chứng lo lắng chia ly nếu bị bỏ rơi trong thời gian dài tại một thời điểm.\n" +
                "Goldendoodle có thể là một người bạn đồng hành tuyệt vời với những người bị dị ứng.\n" +
                "Goldendoodle là “chó thiết kế”, một giống chó lai tạo ra từ việc trộn  Poodle với  Golden Retriever . Giống như tất cả các \"giống\" nhà thiết kế khác, Doodle này không thực sự là một giống của riêng nó, mà là một giống lai - và trong trường hợp này, một con lai đang ngày càng phổ biến",""));
        dogModels.add(new DogModel("Great Dane",R.drawable.great_dane,"0,6 - 0,9m","45 - 90kg","Ai Cập","7 - 10 năm","Mỹ","Great Dane ngọt ngào, háo hức làm hài lòng, hướng về mọi người, dễ huấn luyện và phản ứng tốt với việc huấn luyện bằng cách sử dụng biện pháp tăng cường tích cực.\n" +
                "Giống như nhiều loài chó khổng lồ khác, Great Danes có tuổi thọ ngắn.\n" +
                "Những người Đan Mạch tuyệt vời đòi hỏi nhiều không gian. Mặc dù chúng là những con chó nhà tuyệt vời, chúng cần rất nhiều chỗ chỉ để di chuyển. Có rất ít thứ mà chúng không thể với tới - quầy bếp và bàn ăn không có vấn đề gì - và đuôi của chúng có thể dễ dàng quét sạch bàn cà phê của bạn.\n" +
                "Mọi thứ đều đắt hơn khi bạn nuôi một con chó lớn - vòng cổ, dịch vụ chăm sóc thú y, phòng ngừa giun tim và thức ăn. Ngoài ra, bạn sẽ cần cả một cái thùng và một chiếc xe đủ lớn để chứa Great Dane của bạn mà không làm vụn chúng thành bánh quy. Và hãy đối mặt với nó, bạn sẽ nhặt được rất nhiều phân.\n" +
                "Phải mất một thời gian để xương và khớp của những con chó lớn như Great Danes ngừng phát triển và trở nên ổn định. Không cho phép chó con Great Dane của bạn nhảy và không dắt chúng chạy bộ cho đến khi chúng được ít nhất 18 tháng tuổi; điều này sẽ làm giảm căng thẳng cho xương và khớp đang phát triển.\n" +
                "Các yêu cầu về chế độ ăn uống đặc biệt của giống chó khổng lồ Dane phải được tuân thủ, nếu không các vấn đề về chỉnh hình có thể phát triển.\n" +
                "Great Danes không đặc biệt phù hợp với các căn hộ hoặc nhà nhỏ, đơn giản vì chúng quá lớn. May mắn thay, chúng không phải là vận động viên nhảy cầu, vì vậy một hàng rào dài 6 feet sẽ chứa chúng.",""));
        dogModels.add(new DogModel("Great Pyrenees",R.drawable.great_pyrenees,"0,6 - 0,8m","38 - 72kg","Dãy núi Pyrenees giữa Pháp và Tây Ban Nha","10 - 12 năm"," Anh, châu Âu và Hoa Kỳ"," Great Pyrenees không sao trong các căn hộ bởi vì anh ta là người dịu dàng. Nhưng nhà có sân rộng thì tốt hơn.\n" +
                "Nếu bạn muốn có một con chó, bạn có thể thả dây xích, đây có thể không phải là con chó dành cho bạn vì suy nghĩ độc lập và xu hướng lang thang của nó.\n" +
                "Mong đợi một số loài rụng lông liên tục và ít nhất một đợt rụng lông lớn mỗi năm. Về mặt thuận lợi, Great Pyrenees chỉ cần khoảng 30 phút chải lông mỗi tuần.\n" +
                "Một Pyrenees có thể khó huấn luyện vì khả năng tự suy nghĩ của mình. Anh ta không phải là đối tượng phù hợp với những người nuôi chó mới hoặc nhút nhát, bởi vì anh ta cần sự kiên định và một người chủ mạnh mẽ, người sẽ hòa nhập với anh ta và huấn luyện với sự củng cố tích cực.\n" +
                "Nó là một con chó trông nhà tuyệt vời cho gia đình, nhưng nó cần xã hội hóa để tránh trở nên nhút nhát hoặc hung dữ với cả chó và người.\n" +
                "Nó phát đạt với gia đình và nên sống trong nhà. Anh ta có thể trở nên buồn chán và phá phách khi bị tách khỏi gia đình hoặc bị bỏ đi sống ở sân sau.\n" +
                "Great Pyrenees thường yêu thương và hòa nhã với những sinh vật nhỏ hơn, vì vậy nó là một con chó tuyệt vời cho các gia đình có trẻ em.\n" +
                "Great Pyrenees hoạt động tốt nhất ở những nơi có khí hậu mát mẻ hơn, nhưng đừng kẹp tóc khi thời tiết nóng bức. Lông cách nhiệt và giữ mát cho cơ thể, vì vậy khi cạo lông, bạn sẽ làm ảnh hưởng đến khả năng bảo vệ tự nhiên khỏi ánh nắng mặt trời.\n" +
                "Nó cần tập thể dục , nhưng không nhiều như bạn nghĩ - 20 đến 30 phút mỗi ngày là tốt.\n" +
                "Mục tiêu trong cuộc sống của giống chó Great Pyrenees là bảo vệ cừu, dê, gia súc, con người, trẻ em, cỏ, hoa, mặt trăng, đồ đạc trong bãi cỏ, người cho chim ăn và bất kỳ động vật ăn thịt thực hoặc tưởng tượng nào có thể xâm phạm không gian cá nhân của bạn . Ồ đúng rồi, và để cho đi, cho đi và cho đi tình yêu vô điều kiện.",""));
        dogModels.add(new DogModel("Gollie",R.drawable.gollie,"0,6 - 0,7m","23 - 34kg","lai giữa Golden Retriever & Collie  tại Scotland","12 - 15 năm","Scotland","Gollie là một giống chó lai tạo. Chúng không phải là giống thuần chủng như bố mẹ Golden Retriever & Collie của chúng.\n" +
                "Màu lông phổ biến nhất của Gollies là đen, nâu, vàng và trắng. Chúng có thể che phủ hết hoặc kết hợp hai đến ba màu.\n" +
                "Đây là một giống chó lai tạo chắc chắn rụng nhiều lông. Chải lông hàng ngày sẽ là chìa khóa để giữ cho bộ lông luôn trong tình trạng khỏe mạnh.\n" +
                "Gollies và trẻ em rất hòa hợp với nhau. Con chó rất vui tươi và khoan dung, có nghĩa là ngay cả trẻ nhỏ cũng hình thành mối quan hệ chặt chẽ với con chó.\n" +
                "Con chó không thích ở một mình trong thời gian dài. Đây chắc chắn không phải là một giống chó lai tạo để xem xét nếu bạn sống trong một căn hộ và đi vắng gần như cả ngày.\n" +
                "Gollie là giống chó có nhu cầu tập thể dục và năng lượng cao. Hãy cân nhắc một giờ là lượng tập thể dục và thời gian ngoài trời tối thiểu tuyệt đối mà bạn cần cung cấp mỗi ngày.",""));
        dogModels.add(new DogModel("Golden Shepherd",R.drawable.golden_shepherd,"0,5 - 0,6m","25 - 38kg","lai giữa German Shepherd & Golden Retriever ","10 đến 14 năm","Mỹ","Golden Shepherds là giống chó hỗn hợp. Chúng không phải là những con thuần chủng như bố mẹ Golden Retriever hoặc German Shepherd Dog.\n" +
                "Màu sắc chính của Golden Shepherds là đen, nâu, đỏ, kem và xanh lam. Đôi khi áo khoác của chúng chắc chắn, và đôi khi chúng có sự pha trộn của nhiều màu sắc.\n" +
                "Mặc dù chúng không phải là lựa chọn tốt cho chó đối với những người bị dị ứng, nhưng bộ lông của chúng khá dễ chăm sóc. Đánh răng tốt mỗi tuần có thể sẽ làm được việc. Họ rụng khá nhiều.\n" +
                "Golden Shepherd không chỉ yêu trẻ em, chúng còn thích sự náo động mà chúng mang theo. Họ sẽ vui vẻ tham dự bữa tiệc sinh nhật của một đứa trẻ, và bạn thậm chí có thể yêu cầu chúng đội mũ dự tiệc. Mặc dù vậy, như với tất cả các loài chó, thời gian chơi với trẻ phải luôn được giám sát.\n" +
                "Nếu Golden Shepherd đã tiếp xúc nhiều với những con chó, mèo và động vật nhỏ khác và được huấn luyện cách tương tác với chúng, chúng cũng sẽ thân thiện với những vật nuôi khác.\n" +
                "Golden Shepherds có mức năng lượng cao. Đảm bảo chó của bạn được đi dạo ít nhất một giờ mỗi ngày. Đi bộ đường dài và các hoạt động mạo hiểm khác được khuyến khích.\n" +
                "Golden Shepherd rất dẻo miệng, và chúng hạnh phúc nhất khi có thứ gì đó, bất cứ thứ gì, mang theo trong miệng. Chúng cũng là một cái máy nhai, vì vậy hãy đảm bảo luôn  có sẵn những món đồ chơi chắc chắn \n" +
                "Golden Shepherd là một giống chó lai giữa giống chó Golden Retriever  và  German Shepherd . Lớn, năng động và trung thành, những chú chuột con này thừa hưởng một số phẩm chất tốt nhất từ cả bố và mẹ của chúng",""));
        dogModels.add(new DogModel("Hokkaido",R.drawable.hokkaido,"0,5 - 0,6m","20 - 29kg","đảo Hokkaido","11 - 13 năm","Nhật Bản","Màu sắc chính của giống chó Hokkaido là trắng, đỏ, đen & rám nắng, đen, vện và màu mè.\n" +
                "Giống chó Hokkaido có thú săn mồi, nhưng chúng rất thông minh và sẽ nghe lời chủ nhân khi có lệnh, điều này giúp chúng dễ dàng huấn luyện.\n" +
                "Hokkaidos thỉnh thoảng sẽ sủa, vì chúng luôn cảnh giác và có thể nhận thấy bất cứ điều gì khác thường. Tuy nhiên, chúng cũng sẽ hú nếu chúng vui vẻ hoặc phấn khích.\n" +
                "Giống chó Hokkaido có bộ lông dày nên được chải lông một hoặc hai lần mỗi tuần để loại bỏ lông chết và ngăn ngừa lông tơ. Tần suất chải lông nên được tăng lên trong thời kỳ rụng lông của chúng.\n" +
                "Chó Hokkaido không thích tắm và phải mất một thời gian dài để lông khô do mật độ dày. Thường nên tắm vài lần một năm để ngăn ngừa các vấn đề.\n" +
                "Bộ lông dày của chúng giúp Hokkaidos có thể chống chọi tốt với thời tiết lạnh giá.\n" +
                "Hokkaidos có thể làm tốt trong các căn hộ miễn là đáp ứng được nhu cầu tập thể dục của họ, nhưng họ có thể thích một ngôi nhà rộng hơn có sân.\n" +
                "Hokkaidos rất tốt với trẻ em và yêu thương mọi thành viên trong gia đình.\n" +
                "Hokkaido là giống chó thuần chủng đến từ Nhật Bản. Thông minh, lanh lợi và tận tụy với gia đình, những chú chó này có những phẩm chất khiến chúng trở thành những người bạn đồng hành tuyệt vời.",""));
        dogModels.add(new DogModel("Huskydoodle",R.drawable.huskydoodle,"0,3 - 0,4m","18 - 27kg","lai giữa Siberian Huskies & Poodles","10 - 14 năm","Bắc Mỹ","Huskydoodle là một giống chó lai. Chúng không phải là những con thuần chủng như Siberian Husky hoặc Poodle bố mẹ của chúng.\n" +
                "Màu sắc chính của Huskydoodles là đen, xám và trắng. Đôi khi chúng sẽ có một số màu của bố mẹ Poodle, chẳng hạn như màu mơ, đỏ hoặc nâu, mặc dù điều này ít phổ biến hơn. Đôi khi áo khoác của chúng chắc chắn, và đôi khi chúng có sự pha trộn của nhiều màu sắc.\n" +
                "Huskydoodle thường được lai tạo với mục đích tái tạo bộ lông ít rụng hơn của Poodle, nhưng ở đó chúng có thể có nhiều đặc điểm lông của Siberian Husky hơn, bao gồm cả việc rụng lông theo mùa. Những con chó này có thể không thân thiện với dị ứng.\n" +
                "Huskydoodles có thể có tâm lý bầy đàn và thường thích sự hiện diện của những con chó khác. Ổ săn mồi của chúng có thể gây trở ngại cho việc hòa hợp với mèo.\n" +
                "Huskydoodles là giống chó gia đình tuyệt vời, vì chúng rất hòa đồng và khá chịu đựng những trò thô bạo vô tình từ trẻ nhỏ. Luôn giám sát thời gian chơi.\n" +
                "Giống chó hỗn hợp này có thể cứng đầu ở các điểm, vì vậy việc huấn luyện là điều tuyệt đối bắt buộc với Huskydoodle.\n" +
                "Hãy đảm bảo Huskydoodle của bạn được đi dạo ít nhất từ nửa tiếng đến một tiếng mỗi ngày với một vài buổi vui chơi vận động tốt và một vài buổi đi bộ ngắn hơn.\n" +
                "Huskydoodle là một giống chó hỗn hợp – lai giữa hai giống chó Siberian Husky và Poodle  . Rất thông minh, tràn đầy năng lượng và hòa đồng, những chú chuột con này thừa hưởng một số đặc điểm tốt nhất từ cả bố và mẹ của chúng.",""));
        dogModels.add(new DogModel("Huskita",R.drawable.huskita,"0,5 - 0,6m","22 - 34kg","lai giữa Akita và Siberian Husky","10 - 13 năm","Bắc Mỹ","Huskitas là giống chó lai. Chúng không phải là những con thuần chủng như Siberian Husky hoặc Akita bố mẹ của chúng.\n" +
                "Màu sắc chính của Huskitas là trắng, đen và kem, hoặc nâu vàng. Chúng có thể rắn, nhưng thường là sự pha trộn của những màu này.\n" +
                "Huskitas thường không được khuyến khích cho những người bị dị ứng. Chúng có xu hướng rụng khá nhiều và sẽ cần một vài lần chải lông tốt mỗi tuần.\n" +
                "Akitas tốt hơn là con chó duy nhất trong một gia đình, trong khi Huskies là động vật sống theo đàn. Thật may mắn khi bốc thăm để tìm ra con đường Huskita của bạn sẽ đi khi gặp các động vật khác.\n" +
                "Huskitas là loài bảo vệ và trung thành với trẻ em, nhưng chúng có thể chết nếu bị ngược đãi. Luôn giám sát giờ chơi và dạy trẻ tương tác với chó đúng cách. Những con chó này có thể thích nhà có trẻ lớn hơn hoặc người lớn.\n" +
                "Huskitas dễ tăng cân và chúng có mức năng lượng cao. Đi bộ đường dài 90 phút mỗi ngày là một điểm khởi đầu tốt. Chúng phát triển mạnh ở ngoài trời nhưng cần sống trong nhà.",""));
        dogModels.add(new DogModel("Horgi",R.drawable.horgi,"0,3 - 0,4m","9 - 22kg","lai giữa Corgis và Huskie","12 - 15 năm","Bắc Mỹ","Horgis là giống chó lai. Chúng không phải là những con thuần chủng như Siberian Husky hoặc Corgi bố mẹ của chúng.\n" +
                "Màu sắc chính của Horgis là đen, kem, đỏ, xám và nâu vàng. Lông của chúng thường là sự pha trộn của hai hoặc nhiều màu.\n" +
                "Horgis là loài rụng nhiều và cần chải lông hàng ngày.\n" +
                "Bởi vì Horgi xuất thân từ dòng dõi cha mẹ đi làm, một chương trình đào tạo mạnh mẽ cần được áp dụng sớm. Chúng rất hay nghịch ngợm và có khả năng săn mồi cao.\n" +
                "Horgis rất thích trẻ em, nhưng nhờ bản năng chăn gia súc, đôi khi chúng cắn vào chân hoặc mắt cá chân của trẻ em. Tuy nhiên, họ là những người ham học hỏi và có thể được đào tạo từ hành vi này khi còn nhỏ.\n" +
                "Horgis sẽ có nhiều năng lượng và thích đi bộ và đi bộ đường dài. Chúng có tính cảnh giác cao nhưng không hung dữ hay lãnh thổ. Những chú chó con này là những chú chó đồng hành tuyệt vời cho những người năng động.",""));
        dogModels.add(new DogModel("Ibizan Hound",R.drawable.ibizan_hound,"0,6 - 0,8m","20 -22kg","đảo Eivissa, Tây Ban Nha","10 - 14 năm","Tây Ban Nha"," Chó săn Ibizan hoạt động tốt trong các căn hộ nếu chúng được vận động đúng cách.\n" +
                "Chúng phải được xích lại bất cứ khi nào chúng không ở trong khu vực có hàng rào an toàn. Beezer có một ổ săn mồi mạnh mẽ và sẽ truy đuổi các đối tượng chuyển động mà không cần chú ý đến lệnh của bạn.\n" +
                "Ibizan Hounds là những vận động viên nhảy giỏi. Cần ít nhất một hàng rào dài 6 foot để nhốt chúng trong một sân. Hàng rào điện tử ngầm không được khuyến khích cho giống chó này.\n" +
                "Người nuôi ong cần tập thể dục hàng ngày . Nếu các yêu cầu tập luyện của chúng không được đáp ứng, chúng có thể trở nên buồn chán và phá phách .\n" +
                "Chó săn Ibizan đực có thể kém thèm ăn khi chúng ở tuổi vị thành niên. Khuyến khích họ ăn, nhưng đừng quá hối lộ thực phẩm, bữa ăn cầu kỳ, hoặc đút bằng tay; đơn giản là bạn sẽ kết thúc với một người kén ăn.\n" +
                "Máy làm ong dễ bị lạnh. Nếu bạn sống ở nơi có khí hậu lạnh hoặc ẩm ướt, hãy mua áo khoác cho chó.\n" +
                "Chó săn Ibizan rất tuyệt vời với trẻ em, nhưng tất cả những con chó nên được giám sát khi chúng ở cùng với trẻ nhỏ.\n" +
                "Những con chó này thường yên tĩnh trong nhà và có thể trở thành những chú chó đi văng nhưng chúng cần được đi dạo hoặc chạy hàng ngày.\n" +
                "Chó săn Ibizan là những tay lướt ván cừ khôi, vì vậy đừng để thức ăn ra ngoài, ngay cả khi bạn nghĩ rằng nó nằm ngoài tầm với của chó.\n" +
                "Chó săn Ibizan nhìn chung không hung dữ nhưng chúng có khả năng săn mồi cao và không thích hợp nhất với những ngôi nhà có động vật nhỏ. Chúng có thể học cách hòa hợp với mèo nếu được nuôi cùng chúng, nhưng mèo ngoài trời và các loài động vật khác là trò chơi công bằng.\n" +
                "Chó săn Ibizan là một giống chó hiếm. Hãy dành thời gian lên danh sách chờ nếu bạn quan tâm đến một trong những con chó này.",""));
        dogModels.add(new DogModel("Icelandic Sheepdog",R.drawable.icelandic_sheepdog,"~0,5m","9 - 14kg","Người Viking mang tới Iceland","14 - 15 năm","Iceland","Người ta cho rằng những người Viking xâm lược đã mang tổ tiên của giống chó này đến Iceland vào thế kỷ thứ IX. Nhờ sự cô lập của Iceland, những chú chó chăn cừu Iceland ngày nay - còn được gọi là Icelandic Spitz hoặc Icelandic Dog - có thể trông rất giống tổ tiên của chúng.\n" +
                "Trong nhiều thế kỷ, Icelandic Sheepdog được sử dụng để bảo vệ đàn gia súc, đặc biệt là cừu non, khỏi những con chim săn mồi, và cho đến ngày nay nó vẫn giữ thói quen quan sát bầu trời và sủa các loài chim. Nó cũng sủa để cảnh báo gia đình về bất cứ điều gì và mọi thứ mà nó nhìn thấy hoặc nghe thấy. Nó quá thân thiện để trở thành một con chó bảo vệ, nhưng bạn sẽ không bao giờ ngạc nhiên trước những vị khách đến thăm.\n" +
                "Dễ mến, tự tin và vui tươi, Chó cừu Iceland hòa đồng với mọi người và những con chó khác. Con đực có xu hướng thoải mái và âu yếm hơn con cái. Được đào tạo với sự kiên định và kiên nhẫn, người Iceland học hỏi nhanh chóng và sẵn sàng",""));
        dogModels.add(new DogModel("Irish Terrier",R.drawable.irish_terrier,"~0,5m","11 -13kg","Scotland","12 - 16 năm","Anh","Chó sục Ireland không nhất thiết phải hòa thuận với bất kỳ con chó nào khác. Chúng sẽ chiến đấu nếu bị một con chó khác thách thức và không chịu lùi bước.\n" +
                "Chó sục Ireland có thể cứng đầu.\n" +
                "Chúng là loài chó săn và sẽ đào bới nếu sân của bạn có chuột chũi hoặc các loài gặm nhấm khác.\n" +
                "Irish Terrier phải có cơ hội thường xuyên để đốt cháy năng lượng của họ.\n" +
                "Chó sục Ireland cần những thử thách về tinh thần như luyện tập và thi đấu để phát triển.\n" +
                "Huấn luyện vâng lời rất được khuyến khích. Lệnh \"đến\" có thể khó dạy.\n" +
                "Họ có thể thống trị và cố gắng tiếp quản gia đình. Bạn phải nhất quán và dạy họ rằng bạn luôn là người phụ trách.\n" +
                "Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng. Hãy tìm một nhà lai tạo có uy tín , người đã kiểm tra những con chó giống của cô ấy để đảm bảo rằng chúng không mắc các bệnh di truyền có thể truyền sang chó con và chúng có tính khí tốt.\n" +
                "Giống chó Ailen Terrier từng được mô tả là “lính canh của người nghèo, bạn của nông dân và là vật ưa thích của quý ông”. Bền chắc và mạnh mẽ, chúng có lợi thế về kích thước thuận tiện, khả năng linh hoạt như một người bạn đồng hành, chó canh gác, điều phối sâu bọ và khả năng huấn luyện cao.",""));
        dogModels.add(new DogModel("Italian Greyhound",R.drawable.italian_greyhound,"0,4 - 0,5m","3 - 7kg","Ý","14 - 15 năm","Ý","Chó săn Ý được lai tạo để săn mồi và vẫn có bản năng săn mồi. Chúng sẽ đuổi theo bất cứ thứ gì di chuyển, kể cả ô tô, vì vậy khi bạn ở bên ngoài, hãy giữ chúng bằng dây xích hoặc trong khu vực có hàng rào.\n" +
                "Giống chó này nhạy cảm với các loại thuốc như thuốc gây mê nhóm barbiturat và thuốc trừ sâu organophosphat. Đảm bảo rằng bác sĩ thú y của bạn biết về những điểm nhạy cảm này, và tránh sử dụng các sản phẩm organophosphate để điều trị bọ chét trong nhà và sân vườn của bạn.\n" +
                "Chó Greyhound Ý không sợ hãi và tin rằng chúng có thể bay. Gãy xương thường gặp ở chuột con từ 4 đến 12 tháng tuổi, đặc biệt là xương bán kính và xương đòn (xương ở chân trước).\n" +
                "Mặc dù chúng rất thông minh, chó săn Ý có khoảng thời gian chú ý ngắn và thường có \"thái độ\" đối với việc đào tạo . Giữ cho các buổi huấn luyện ngắn và tích cực, sử dụng cách chơi, trò chơi và khen ngợi để thúc đẩy Greyhound Ý của bạn học hỏi.\n" +
                "Giống chó này có thể cực kỳ khó săn mồi . Ngay cả khi bạn theo một chương trình huấn luyện săn chuột về mặt tôn giáo, Chó săn Ý của bạn có thể không bao giờ hoàn toàn đáng tin cậy trong nhà. Sẽ rất hữu ích khi có cửa cho chó để chó có thể ra vào theo ý muốn. Và nếu con chó của bạn có dấu hiệu cho thấy nó cần đi ra ngoài, hãy đưa nó ra ngoài ngay lập tức - chúng không giỏi trong việc bế nó.\n" +
                "Chó săn Ý cần nhiều tình yêu thương và sự quan tâm, và nếu không nhận được điều đó, chúng sẽ trở nên nhút nhát hoặc quá khích.\n" +
                "Giống chó Greyhound của Ý từng là bạn đồng hành yêu thích của phụ nữ quý tộc trong thời Trung cổ, đặc biệt là ở Ý. Nhưng con chó săn nhỏ này còn hơn cả một con chó lai, có tốc độ, sức bền và quyết tâm săn trò chơi nhỏ. Ngày nay, chúng là một con chó gia đình có vẻ đẹp và sự thể thao được ngưỡng mộ trên sàn đấu và trong các cuộc thi vâng lời, nhanh nhẹn và tập hợp",""));
        dogModels.add(new DogModel("Irish Wolfhound",R.drawable.irish_wolfhound,"~0,8m","52 - 81kg","Iceland","6 đến 8 năm","Iceland","Chó sói Ailen không được khuyến khích sống trong căn hộ. Mặc dù chúng có mức độ hoạt động tương đối thấp bên trong, chúng cần có chỗ để giãn ra và không được xây dựng để làm cầu thang đàm phán.\n" +
                "Chó săn sói Ailen cần ít nhất 40 phút tập thể dục hàng ngày và hoạt động tốt nhất trong nhà có sân rộng có hàng rào.\n" +
                "Chó săn sói Ailen cần một sân có hàng rào để ngăn chúng đuổi con mồi ra khỏi sân của chúng. Chúng không nên được giữ trong sân có hàng rào điện tử ngầm.\n" +
                "Chó sói Ailen là một giống chó hiền lành, thường hòa đồng với mọi người. Với sự xã hội hóa và huấn luyện sớm , nó sẽ hòa nhã với những con chó khác và cấm mèo trong nhà. Anh ấy sẽ coi mèo ngoài trời và các động vật khác là trò chơi công bằng.\n" +
                "Nếu bạn đang tìm kiếm một giống chó sống lâu, thì Irish Wolfhound không dành cho bạn. Anh ta sống khoảng 6 đến 8 năm và kích thước khổng lồ của nó khiến nó gặp nhiều vấn đề về sức khỏe.\n" +
                "Chó săn sói Ailen không phải là giống chó bảo vệ tốt mặc dù kích thước của chúng có thể là yếu tố ngăn chặn những kẻ đột nhập.\n" +
                "Chó săn sói Ailen là loài rụng lông trung bình và chỉ cần được chải lông hàng tuần hoặc hai tuần một lần. Bạn sẽ cần phải lột những phần lông dài hơn của nó nếu bạn muốn giữ cho nó trông giống như Chó sói Ailen bình thường khác.\n" +
                "Chó săn sói Ailen nên được dắt bằng dây xích để ngăn chúng đuổi theo động vật hoặc các vật thể chuyển động khác, chẳng hạn như ô tô điều khiển bằng sóng radio.\n" +
                "Ailen Wolfhound không phải là một con ngựa và không nên cho trẻ em cưỡi, cho dù nhỏ đến đâu. Các khớp của nó không được tạo ra để chống lại lực kéo. Nó cũng không được chế tạo để kéo một chiếc xe đẩy hoặc phương tiện khác.\n" +
                "hó săn sói Ailen phát triển mạnh khi chúng ở cùng chủ. Chúng không phải là những con chó ngoài trời, mặc dù chúng thích chơi bên ngoài.",""));
        dogModels.add(new DogModel("Jack-A-Poo",R.drawable.jackapoo,"0,2 - 0,4m","6 - 11kg","lai giữa Jack Russell Terrier & Poodle","12 - 15 năm","Hoa Kỳ","Jack-A-Poo là một giống chó lai. Chúng không phải là những con thuần chủng như Jack Russell Terrier hoặc Poodle bố mẹ của chúng.\n" +
                "Màu lông Jack-A-Poo bao gồm trắng, đen, nâu, nâu, xám và xanh - và áo khoác có thể là sự kết hợp của những màu này. Hoa hồng luôn đen, và mắt luôn nâu.\n" +
                "Chải lông khá ít tốn công, chỉ cần chải lông mỗi tuần một lần và tắm khi cần thiết.\n" +
                "Jack-A-Poos có thể tuyệt vời với trẻ em, miễn là chúng được hòa nhập với xã hội ngay từ khi còn nhỏ. Như với tất cả các con chó, bạn nên giám sát tương tác với trẻ nhỏ.\n" +
                "Chúng có thể hòa thuận với những con chó và mèo khác, nhưng nếu không thể xã hội hóa sớm, tốt nhất bạn nên ở nhà một vật nuôi. Ngoài ra, tốt nhất bạn không nên khuyến khích tương tác giữa các động vật nhỏ, như động vật gặm nhấm và Jack-A-Poos, vì có thể khó kìm hãm bản năng thợ săn của chúng.\n" +
                "Jack-A-Poos đứng đầu bảng về năng lượng và nhu cầu tập thể dục. Họ nên có ít nhất 45 đến 60 phút tập thể dục mỗi ngày.\n" +
                "Việc huấn luyện và xã hội hóa sớm là rất quan trọng đối với tất cả các con chó, nhưng đặc biệt là Jack-A-Poos, do chúng pha trộn giữa trí thông minh với tính cách cứng đầu. Điều này sẽ giúp hạn chế xu hướng sủa và làm chúng mất mẫn cảm với trẻ em, động vật khác và người lạ.",""));
        dogModels.add(new DogModel("Jack Chi",R.drawable.jack_chi,"0,3 - 0,4m","4 - 8kg","lai giữa Jack Russell Terrier & Chihuahua tại Hoa Kỳ","13 - 18 năm","Vương quốc Anh, Hoa Kỳ","Jack Chi là một giống chó lai. Chúng không phải là những con thuần chủng như Jack Russell Terrier hoặc Chihuahua bố mẹ của chúng.\n" +
                "Jack Chis rất thích sự đồng hành của con người và thậm chí là những người bạn tuyệt vời cho những đứa trẻ biết cách tương tác đúng mực với những chú chó nhỏ.\n" +
                "Jack Chis dễ bị dị ứng da và ngứa. Nói chuyện với bác sĩ thú y của bạn về các phương pháp điều trị dị ứng nếu đây là trường hợp của con chó của bạn.\n" +
                "Lông của Jack Chi có thể có các màu đen, đen, nâu vàng, vàng, kem và trắng. Chúng thường là sự kết hợp của hai màu hoặc trong một số trường hợp là ba màu.\n" +
                "Bộ lông của chúng dày và thẳng, mặc dù Jack Chis có thể cần được bảo vệ thêm bằng lông xù trong thời tiết lạnh.\n" +
                "Jack Chi có thể thích nghi với cuộc sống chung cư rất tốt, mặc dù chúng vẫn đòi hỏi sự kích thích về thể chất và tinh thần. Đồ chơi tương tác sẽ giúp ngăn chặn sự nhàm chán.",""));
        dogModels.add(new DogModel("Jack Russell Terrier",R.drawable.jack_russell_terrier,"0,3 - 0,4m","6 - 8kg","Miền nam nước Anh","10 - 15 năm","Miền nam nước Anh"," Giống như nhiều loài chó sục khác , Jack Russell Terrier thích đào bới và có thể tạo ra một cái hố khá lớn trong thời gian ngắn. Huấn luyện một con chó đào bới ở một khu vực cụ thể sẽ dễ dàng hơn là phá bỏ thói quen đào bới của nó.\n" +
                "Jack Russell Terriers phải có sân được rào chắc chắn để chúng có chỗ chơi đùa và đốt cháy nguồn năng lượng dồi dào. Hàng rào điện tử ngầm sẽ không giữ chúng. Jacks được biết là trèo cây và thậm chí hàng rào liên kết chuỗi để thoát khỏi sân của chúng, vì vậy tốt nhất là thời gian ở ngoài trời của chúng được giám sát.\n" +
                "Những người nuôi chó lần đầu hoặc nhút nhát nên chọn loại chó khác. Jack có thể là một thách thức ngay cả đối với một người nuôi chó có kinh nghiệm. Anh ấy có ý chí mạnh mẽ và cần được đào tạo kiên định và bền bỉ .\n" +
                "Jack có thể là loài chó sủa giải trí , vì vậy chúng không phù hợp với cuộc sống chung cư.\n" +
                "Sự hung dữ đối với những con chó khác có thể là một vấn đề nghiêm trọng với Jack Russell Terrier nếu nó không được dạy để hòa hợp với những chiếc răng nanh khác ngay từ khi còn nhỏ.\n" +
                "Jack Russell phát triển mạnh khi ở cùng gia đình và không nên sống ngoài trời hoặc trong cũi. Khi bạn ra khỏi nhà, hãy thử bật radio để giúp ngăn chặn sự lo lắng khi chia tay .\n" +
                "Jacks rất vui tính và sẽ nhảy lên mọi người và mọi thứ. Chúng có khả năng nhảy cao hơn 1,5m.\n" +
                "Jack Russells có một ổ săn mồi mạnh mẽ và sẽ cất cánh sau những động vật nhỏ hơn. Chúng không bao giờ được tin cậy khi bị buộc dây trừ khi chúng ở trong khu vực có hàng rào.\n" +
                "Jack Russell Terriers có mức năng lượng cao và hoạt động trong nhà và ra ngoài. Họ cần đi bộ vài lần mỗi ngày, hoặc chơi vài trận hay trong sân. Họ là những người bạn đồng hành tuyệt vời.",""));
        dogModels.add(new DogModel("Kai Ken",R.drawable.kai_ken,"0,4 - 0,6m","11 - 20kg","Vùng núi Kai, Yamanashi, Nhật Bản","12 - 16 năm","Nhật Bản","Kai Ken đôi khi được gọi là Tiger Dog vì bộ lông vện đặc biệt của chúng, thường có ba biến thể: vện đen (Kuro-Tora), vện (Chu-Tora) và vện đỏ (Aka-Tora), với màu đỏ là loại hiếm nhất trong số đó.\n" +
                "Bộ lông của Kai Ken cũng là một lớp lông kép, chúng sẽ rụng theo mùa. Điều này có thể không làm cho chúng trở thành lựa chọn tốt nhất cho những người bị dị ứng.\n" +
                "Chó Kai Ken có mức năng lượng cao. Đảm bảo rằng con chó của bạn được đi bộ ít nhất nửa giờ đến một giờ mỗi ngày với một vài buổi vui chơi vận động tốt và kết hợp đi bộ ngắn hơn.\n" +
                "Kai Ken là một người bạn bình tĩnh đáng kinh ngạc cho trẻ em. Loài chó này không phải là giống chó thích nhà ở thô sơ, nhưng chúng thường không trở nên hung dữ đối với những đứa trẻ béo tốt.\n" +
                "Thông thường, Chó hổ không có nhiều vấn đề với những con chó khác, nhưng bản năng săn mồi của chúng có thể quá khó để bỏ qua khi có mèo hoặc vật nuôi nhỏ khác.",""));
        dogModels.add(new DogModel("Chó gấu Karelian",R.drawable.karelian,"0,5 - 0,6m","20 - 23kg","Phần Lan","10 - 13 năm","Bắc Mỹ, Châu Âu.","Chó gấu Karelian đôi khi được đánh vần là \"Chó gấu Carelian\" với chữ \"C\" ở Phần Lan, nơi bắt nguồn của giống chó này. Nó còn được gọi là Karjalankarhukoira trong tiếng Phần Lan.\n" +
                "Chó gấu Karelian được lai tạo để săn các loài thú lớn, hung dữ như gấu, linh miêu, lợn rừng, chó sói và nai sừng tấm.\n" +
                "Giống chó này bản chất hung dữ với các động vật khác, vì vậy sẽ rất cần sự xã hội hóa nếu chúng được nuôi làm bạn đồng hành trong gia đình. Những con chó này có thể hoạt động tốt nhất trong nhà không có vật nuôi khác.\n" +
                "Tại Hoa Kỳ, chó Karelian Bear đã được sử dụng tại Công viên Quốc gia Yosemite và Glacier để kiểm soát gấu, cũng như với Bộ Cá và Động vật hoang dã của Tiểu bang Washington.\n" +
                "lông của chó Karelian Bear hiện đại chủ yếu là màu đen với các mảng màu trắng, và một số có màu nâu nổi bật trên bộ lông đen của chúng.\n" +
                "Chó gấu Karelian vẫn được đánh giá cao bởi các thợ săn trò chơi lớn, nhưng đối với những người muốn có một con vật cưng trong nhà, chúng cũng trở thành những con chó canh gác tuyệt vời.\n" +
                "Tổ tiên của Chó gấu Karelian có thể được bắt nguồn từ thời đồ đá mới, theo các phát hiện khảo cổ học, khi những con chó tương tự như giống chó hiện đại theo chân những người định cư đến các khu vực của Scandinavia và châu Âu.",""));
        dogModels.add(new DogModel("Kerry Blue Terrier",R.drawable.kerry_blue_terrier,"0,4 - 0,5m","15 - 18kg","Ireland","12 - 15 năm","Ireland","Kerry Blue Terry là một học viên nhanh, mặc dù nó có thể có ý chí mạnh mẽ đôi khi. Bạn sẽ cần rất nhiều kiên nhẫn và sự rắn rỏi, cộng với óc hài hước tốt khi huấn luyện giống chó này.\n" +
                "Kerry Blue thân thiện với mọi người, nhưng sự chán ghét của nó đối với những con chó khác thì ai cũng biết. Nó có thể hung hăng và hay gây gổ. Chủ sở hữu phải cảnh giác khi mang Kerry Blue ở nơi công cộng. Nếu được hòa đồng và được huấn luyện tốt , nó có thể sẽ không đánh nhau, nhưng anh ấy có thể cố gắng kết thúc nó nếu bị chế nhạo.\n" +
                "Giữ cho Kerry Blue của bạn được chải chuốt rất tốn kém và nếu bạn tự làm thì đó là một công việc khó khăn.\n" +
                "Giống như tất cả các giống chó sục khác, Kerry Blue có thể hung dữ. Nó thích đào bới , đuổi bắt , nhai và đôi khi sủa .\n" +
                "Đây là một giống chó năng động. Anh ấy cần tập thể dục nhiều , mỗi ngày. Tốt nhất là sân để chơi kết hợp với đi dạo hàng ngày.\n" +
                "Kerry Blue Terrier là giống chó lao động tinh túy . Chúng đến từ County Kerry, Ireland, nơi chúng được lai tạo để săn thú nhỏ và chim, giết loài gặm nhấm, chăn cừu và gia súc. Thông minh và dũng cảm, chúng cũng trở thành một con vật cưng được yêu mến, thể hiện sự tận tâm mãnh liệt đối với gia đình hoặc bầy đàn của chúng.",""));
        dogModels.add(new DogModel("Keeshond",R.drawable.keeshonds,"~0,3m","16 - 20kg","Hà Lan","12 - 15 năm","Mỹ, Hà Lan","Keeshond không bao giờ miễn cưỡng phát ra tiếng sủa cảnh báo để cảnh báo gia đình mình với người lạ. Xu hướng sủa của nó có thể là một vấn đề nếu nó ở một mình quá nhiều và trở nên buồn chán.\n" +
                "Cách tốt nhất để khiến Keeshond không đau khổ là giữ  nó cùng với gia đình. Nó được lai tạo để trở thành một người bạn đồng hành , và nó cần trở thành một phần của cuộc sống gia đình. Nếu bạn không muốn một con chó tham gia vào các bữa tiệc nướng của gia đình, trò chơi bài hoặc thời gian xem phim, hãy cân nhắc một giống chó độc lập hơn.\n" +
                "Giữ bộ lông Keeshond trong tình trạng tốt không quá khó, nhưng giống chó này sẽ rụng lông như điên một hoặc hai lần hoặc mỗi năm. May mắn thay, thường không cần tắm thường xuyên - Keeshond cho điểm thấp về mùi doggie.\n" +
                "Con đực cao 18 inch và nặng khoảng 45 pound. Con cái cao 17 inch và nặng khoảng 35 pound.",""));
        dogModels.add(new DogModel("King Shepherd",R.drawable.king_shepherd,"0,6 - 0,8m","34 - 68kg","Mỹ","10 -11 năm","Mỹ","King Shepherd là một giống chó lai. Chúng là sự pha trộn của nhiều giống chó khác nhau, nhưng luôn có tổ tiên là German Shepherd.\n" +
                "Màu sắc chính của King Shepherds là nâu vàng, đỏ, đen, nâu và xám. Áo khoác của họ thường là sự kết hợp của hai hoặc nhiều màu.\n" +
                "Mặc dù chúng không phải là lựa chọn tốt cho chó đối với những người bị dị ứng, nhưng bộ lông của chúng khá dễ chăm sóc. Đánh răng tốt ba lần một tuần có thể sẽ làm tốt công việc chải chuốt khác khi cần thiết.\n" +
                "King Shepherd rất hòa thuận với trẻ em, đặc biệt là những đứa trẻ đã được nuôi dưỡng cùng. Một người khổng lồ hiền lành, họ kiên nhẫn và ngọt ngào với trẻ em.\n" +
                "Chúng cũng hòa thuận với chó và các vật nuôi khác trong nhà, kể cả mèo. Xã hội hóa sớm là một yếu tố quan trọng cho sự phát triển của nó trong môi trường gia đình.\n" +
                "King Shepherd có khả năng huấn luyện cao và phát triển mạnh nhờ sự củng cố tích cực. Đừng để chúng một mình trong thời gian dài. Nó có thể dễ dàng trở nên buồn chán, chán nản và thất vọng dẫn đến những hành vi không mong muốn.\n" +
                "King Shepherd là một con chó đồng hành chăm chỉ, trung thành. Tự tin và mạnh mẽ, kích thước khổng lồ của chúng sẽ là một sự ngăn cản khá lớn đối với bất kỳ kẻ săn mồi nào. King Shepherd bảo vệ gia đình của họ, nhưng chúng không phải là những con chó hung hãn.\n" +
                "King Shepherd là sự kết hợp của một số giống chó có thể có nhưng phải kể đến  German Shepherd . Thông thường, chúng được pha trộn với  Alaskan Malamute và / hoặc Great Pyrenees , và một số dòng cũ hơn có nguồn gốc từ Akita . Mặc dù chúng có kích thước đáng sợ, nhưng chúng rất tình cảm và yêu thương.\n",""));
        dogModels.add(new DogModel("Komondor",R.drawable.komondor,"0,6 - 0,7m","36 -45kg","Hungary","10 - 12 năm","Hungary","Komondor rất hiếm, nhưng những người chăn nuôi phi đạo đức và các nhà máy sản xuất chó con vẫn nuôi chúng. Điều quan trọng là không bao giờ mua chó từ một người chăn nuôi thiếu trách nhiệm hoặc cửa hàng vật nuôi có nguồn cung cấp chó con từ các nhà máy.\n" +
                "Mặc dù một căn hộ hoặc chung cư không phải là không gian sống lý tưởng cho Komondor, chúng có thể điều chỉnh theo lối sống đó nếu chúng được tập thể dục hàng ngày và được huấn luyện không sủa quá mức.\n" +
                "Loài chó có ý chí mạnh mẽ này cần một người chủ tự tin có thể cung cấp khả năng lãnh đạo mà Komondor sẽ tôn trọng. Đây không phải là lựa chọn tốt cho những người lần đầu nuôi chó.\n" +
                "Mặc dù không nên chải lông cho Komondor, nhưng bộ lông của chúng cần được chăm sóc kỹ lưỡng để giữ được màu trắng và không bị dính bụi bẩn, mảnh vụn và ký sinh trùng. Bạn có thể cần đến thăm người chăm sóc tóc thường xuyên.\n" +
                "Komondor là những kẻ hay sủa và nghi ngờ hầu hết những thứ chúng nhìn thấy hoặc nghe thấy. Giống chó này là một giống chó canh gác tuyệt vời cho cả gia đình và gia súc và ban đầu được phát triển cho vai trò này.\n" +
                "Komondorok có thể gây hấn với những con chó khác .\n" +
                "Komondorok không có năng lượng cao và rất vui khi chỉ quan sát và theo dõi bạn trong nhà. Nhưng họ vẫn cần tập thể dục hàng ngày, ít nhất vài lần đi bộ mỗi ngày để giữ cho họ khỏe mạnh và có cân nặng phù hợp .\n" +
                "Cần có một hàng rào cao để ngăn chặn Komondor âm mưu mở rộng lãnh thổ của chúng, một thói quen phổ biến của chó bảo vệ.\n" +
                "Komondor hạnh phúc nhất khi họ làm việc. Chúng rất lý tưởng để bảo vệ gia súc, nhưng bất kỳ công việc nào cũng sẽ giúp chúng rèn luyện tinh thần.\n" +
                "Mặc dù trong lịch sử, Komondor dành thời gian bên ngoài để bảo vệ bầy, nhưng chúng cần thời gian ở bên trong với gia đình. Giống như bất kỳ loài chó nào, Komondor có thể trở nên hung dữ, sợ hãi hoặc xa cách khi không có sự đồng hành của con người.\n" +
                "Mặc dù vẻ ngoài của Komondor có thể khiến bạn nghĩ rằng chúng được phát triển để lau sàn, nhưng loài chó này có một di sản lâu đời và cao quý là một giống chó bảo vệ bầy đàn ở quê hương Hungary của chúng. Họ vẫn giữ một bản năng bảo vệ mạnh mẽ và sẽ bảo vệ gia đình và tài sản của họ bằng mạng sống của họ.",""));
        dogModels.add(new DogModel("Lab Pointer",R.drawable.lab_pointer,"0,5 - 0,7m","16 - 36kg","lai giữa Labrador Retriever & Pointer","10 - 15 năm","Hoa Kỳ","Lab Pointer là một giống chó hỗn hợp. Chúng không phải là những con thuần chủng như bố mẹ Labrador Retriever hoặc Pointer.\n" +
                "Các màu lông phổ biến nhất của Lab Pointer là trắng kem, nâu và đen.\n" +
                "Tập thể dục và thời gian ngoài trời là rất quan trọng đối với Lab Pointer. Đường đi bộ nên ở phía dài hơn và chắc chắn bạn sẽ cần đến công viên dành cho chó không dây hoặc một nơi an toàn để chó có thể chạy xung quanh tự do.\n" +
                "Các con trỏ phòng thí nghiệm có thể có ổ mồi cao hơn bình thường, vì vậy hãy đảm bảo không để chúng không được giám sát.\n" +
                "Vì Lab Pointer là một giống chó bảo dưỡng tương đối thấp, bạn sẽ chỉ cần chải lông cho chó thỉnh thoảng; mặc dù trong những tháng nóng hơn, giống chó này sẽ rụng lông nhiều hơn, vì vậy bạn sẽ cần tăng tần suất chải lông.\n" +
                "Chó Lab Pointer và trẻ em rất vui vẻ bên nhau. Chúng sẽ hình thành mối quan hệ bền chặt và trở thành bạn cùng chơi, nhưng việc đào tạo và xã hội hóa sớm là rất quan trọng.\n" +
                "Lab Pointer là một giống chó hỗn hợp - con lai giữa hai giống chó Labrador Retriever và Pointer . Hoạt bát, trung thành và thông minh, những chú chó con này thừa hưởng một số phẩm chất tốt nhất từcả bố và mẹ của chúng.",""));
        dogModels.add(new DogModel("Labsky",R.drawable.labsky,"0,5 - 0,7m","18 - 27kg","lai giữa Labrador Retriever & Siberian Husky","10 - 12 năm","Alaska và Canada","Labsky là một giống chó hỗn hợp. Chúng không phải là những con thuần chủng như bố mẹ Labrador Retriever hoặc Siberian Husky.\n" +
                "Labskies lần đầu tiên được lai tạo có chủ đích vào những năm 1990.\n" +
                "Labsky là giống chó có kích thước từ trung bình đến lớn với năng lượng cao.\n" +
                "Labskies có xu hướng yêu thích mọi người và sự tiếp xúc của con người. Chúng thường rất thân thiện.\n" +
                "Labsky có bộ lông kép cần chải lông hàng ngày.\n" +
                "Bộ lông của chúng có thể có các màu trắng, đen, vàng, nâu, đỏ và xám, và nhiều khi bộ lông sẽ là sự pha trộn của nhiều màu sắc.\n" +
                "Labskies có thể có khả năng săn mồi cao và có thể phù hợp nhất với những ngôi nhà có vật nuôi cùng kích thước.",""));
        dogModels.add(new DogModel("Maltese",R.drawable.maltese_dog5_1,"0,2 - 0,3m","~3kg","lưu vực Địa Trung Hải","12 - 15 năm","Địa Trung Hải","Maltese dễ bị ớn lạnh, đặc biệt nếu chúng bị ẩm ướt hoặc đi lại ở những khu vực ẩm ướt.\n" +
                "Nếu Maltese của bạn để tóc dài, nó có thể bị cháy nắng ở vùng da có phần tóc ở phía sau.\n" +
                "Vì kích thước nhỏ và cấu trúc mỏng manh, Maltese thường không được khuyến khích cho các hộ gia đình có trẻ mới biết đi hoặc trẻ nhỏ.\n" +
                "Một số loài Maltese có hệ tiêu hóa nhạy cảm và có thể kén ăn. Các vấn đề về ăn uống có thể xảy ra nếu Maltese của bạn cũng có vấn đề về răng hoặc nướu . Nếu Maltese của bạn tỏ ra khó chịu khi ăn hoặc sau khi ăn, hãy đưa nó đến bác sĩ thú y để kiểm tra.\n" +
                "Để có được một chú chó khỏe mạnh, đừng bao giờ mua một chú chó con từ một người chăn nuôi thiếu trách nhiệm, nhà máy sản xuất chó con hoặc cửa hàng thú cưng. Hãy tìm một nhà lai tạo có uy tín , người đã kiểm tra những con chó giống của cô ấy để đảm bảo rằng chúng không mắc các bệnh di truyền có thể truyền sang chó con và chúng có tính khí tốt.\n" +
                "Là một giống chó hiền lành và không sợ hãi , Maltese chào đón mọi người như một người bạn. Bộ lông trắng quyến rũ mang đến cho họ vẻ quý phái kiêu kỳ, nhưng vẻ ngoài có thể đánh lừa.",""));
        dogModels.add(new DogModel("Maltipoo",R.drawable.maltipoo,"0,2 - 0,3m","3 - 9kg","lai giữa Maltese & Poodles","10 - 13 năm","Bắc Mỹ ","Maltipoos năng động và tràn đầy năng lượng. Nó cần hàng ngày tập thể dục, như đi bộ, nô đùa trong sân.\n" +
                "Sủa có thể là một trò tiêu khiển yêu thích của Maltipoo. Chúng làm chó canh gác tuyệt vời, sủa cảnh báo để cảnh báo bạn về bất cứ điều gì đáng ngờ, nhưng chúng có thể không phải là lựa chọn tốt nhất cho những người nhạy cảm với tiếng ồn hoặc những người sống trong nhà ở hạn chế tiếng ồn.\n" +
                "Maltipoos rụng ít. Chúng được coi là tốt cho những người bị dị ứng , nhưng hãy nhớ rằng không có thứ gì gọi là chó thực sự ít gây dị ứng. Tất cả các con chó đều tiết ra lông và nước bọt, mang theo chất gây dị ứng. Cách tốt nhất để biết Maltipoo có gây dị ứng cho bạn hay không là dành nhiều thời gian cho nó.\n" +
                "Maltipoos cần chải lông hàng ngày và tắm hàng tháng để giữ cho bộ lông của chúng sạch sẽ và không bị rối.\n" +
                "Yêu thương và hiền lành, Maltipoo hòa đồng với trẻ em. Nhưng vì những con Maltipoos nhỏ có thể dễ bị thương nên chúng chỉ được khuyên dùng cho những gia đình có trẻ em trên sáu tuổi biết cách chăm sóc chó.\n" +
                "Maltipoos rất thông minh và có thể dễ dàng huấn luyện .\n" +
                "Maltipoos thường hòa thuận với những con chó và vật nuôi khác.\n" +
                "Maltipoos có thể hoạt động tốt trong các căn hộ và nhà riêng. Chúng có mức năng lượng cao trong nhà.\n" +
                "Maltipoos là những con chó đồng hành và có thể bị lo lắng về sự chia ly nếu chúng thường xuyên bị bỏ lại một mình trong thời gian dài.",""));
        dogModels.add(new DogModel("Manchester Terrier",R.drawable.manchester_terrier,"~0,4m","5 -10kg","Anh","14 - 16 năm","Manchester","Nó xuất sắc trong các môn vận động nhanh nhẹn, vâng lời.\n" +
                "Chúng là loài chó canh gác tuyệt vời và sẽ sủa nhiệt tình nếu không được huấn luyện để im lặng khi ra lệnh.\n" +
                "Manchester Terrier có thể cứng đầu và khó đào tạo.\n" +
                "Chúng năng động và thích đi dạo. Cẩn thận ở những khu vực không có dây buộc hoặc không an toàn; khi bản năng săn mồi của chúng phát huy, việc huấn luyện sẽ không còn nữa. Đó là tất cả về cuộc rượt đuổi .\n" +
                "Chúng sủa, đào và giết sâu bọ và sinh vật nhỏ, kể cả vật nuôi có túi.",""));
        dogModels.add(new DogModel("Mastador",R.drawable.mastador,"0,7 - 0,9m","38 - 72kg","Bắc Mỹ","10 - 12 năm","Bắc Mỹ","Mastador là một giống chó hỗn hợp. Chúng không phải là những con thuần chủng như Mastiff và Labrador Retriever bố mẹ của chúng.\n" +
                "Màu sắc chính của Mastadors là nâu, đen, vàng và vện. Đôi khi lông của chúng một màu, và đôi khi chúng có sự pha trộn của nhiều màu sắc.\n" +
                "Mặc dù Mastadors khá dễ chải chuốt, nhưng chúng không phải là lựa chọn tốt cho những người bị dị ứng.\n" +
                "Mastadors yêu trẻ con. Điều đó nói lên rằng, chúng là những con chó lớn, hiếu động và có thể vô tình đánh ngã một đứa trẻ mới biết đi chỉ bằng một cái vuốt đuôi. Luôn giám sát trò chơi, ngay cả với một con chó đã được huấn luyện.",""));
        dogModels.add(new DogModel("Neapolitan Mastiffs ",R.drawable.neapolitan_mastiffs,"0,6 - 0,8m","54 - 90kg","miền nam nước Ý","8 - 10 năm","Châu Âu","Neapolitan Mastiffs hoạt động tốt nhất trong những ngôi nhà có sân mà chúng có thể tuần tra. Tuy nhiên, chúng điềm tĩnh khi ở trong nhà và có thể sống tốt trong một căn hộ hoặc căn hộ đủ lớn để chứa chúng.\n" +
                "Neos nói chung là những con chó vụng về, gặp khó khăn khi điều hướng nhiều bậc thang, đặc biệt là khi còn là chó con.\n" +
                "Neapolitan Mastiff là loài rụng lông trung bình và cần chải lông hàng tuần, cộng với việc chú ý làm sạch các nếp nhăn và nếp gấp trên da của chúng.\n" +
                "Chúng là một biện pháp ngăn chặn tuyệt vời đối với những kẻ xâm nhập, nhưng hiếm khi hung hãn vô cớ. Giao lưu với chúng sớm và thường xuyên để chúng học cách cư xử với những người và động vật khác.\n" +
                "Neapolitan Mastiff có thể lười biếng và sẽ trở nên béo phì nếu chúng không được vận động nhiều. Đảm bảo rằng con chó của bạn duy trì trọng lượng khỏe mạnh để tránh các bệnh có thể làm giảm đáng kể tuổi thọ của chúng.\n" +
                "Neapolitan Mastiff không được khuyến khích cho những chủ sở hữu nhút nhát hoặc lần đầu tiên. Giống chó này cần một người huấn luyện tự tin, người kiên định và chắc chắn nhưng cũng yêu thương. Neo là người có ý chí mạnh mẽ và sẽ kiểm tra xem bạn có thực sự muốn nói gì không.\n" +
                "Neapolitan Mastiff có vẻ ngoài đáng sợ và vỏ sâu, cả hai điều này thường là quá đủ để ngăn chặn ngay cả những tên tội phạm ngu ngốc nhất.\n" +
                "Neapolitan Mastiff có một số thói quen gây khó chịu cho con người: lười biếng, chảy nước dãi, thở khò khè, càu nhàu, khịt mũi và đầy hơi.\n" +
                "Con chó trìu mến này không nhận thức được kích thước của chúng và sẽ vui vẻ ôm ấp bạn hoặc bạn. [ Lưu ý: Chúng tôi biết về một Neo có gia đình dựng cây thông Noel vào dịp Halloween và không hạ nó cho đến tháng 2 vì anh ấy thích nằm quanh gốc cây và họ tự mua ghế sofa cho mình trong vài tháng.]\n" +
                "Chó Mastiff Neapolitan yêu thích hoạt động ngoài trời, nhưng chúng cũng thích ở bên gia đình. Họ nên sống trong nhà với người của họ, không phải một mình ở sân sau.",""));
        dogModels.add(new DogModel("Newfoundland",R.drawable.newfoundland,"~0,6m","45 - 68kg","Canada","8 - 10 năm","miền Đông Canada","Newfoundland là một con chó lớn khi trưởng thành. Mặc dù hiền lành, nó không phải là con chó căn hộ một phòng ngủ cơ bản của bạn và có lẽ sẽ hạnh phúc hơn trong một khung cảnh rộng rãi hơn.\n" +
                "Anh ấy có tinh thần làm việc mạnh mẽ, cần tập thể dục và kích thích tinh thần. Huấn luyện liên tục và các môn thể thao cho chó là một lối thoát hoàn hảo cho khả năng làm việc của nó.\n" +
                "Nếu bạn không thể chịu được sự lười biếng của chó, Newfoundland không dành cho bạn. Giống chó này chảy nước dãi. Rất nhiều.\n" +
                "Để giữ cho bộ lông dày của Newfoundland trông đẹp, anh ta cần chải chuốt thường xuyên. Bạn có thể tự làm, việc này tốn nhiều thời gian, hoặc bạn có thể thuê một người chải chuốt chuyên nghiệp, điều này có thể tốn kém.\n" +
                "Newfoundland phát triển mạnh ở vùng khí hậu mát mẻ, mặc dù nó có thể thích nghi để sống ở vùng khí hậu ấm hơn. Để bảo vệ trẻ khỏi bị say nóng, hãy để trẻ gần máy lạnh hoặc quạt khi trời thực sự nóng.",""));
        dogModels.add(new DogModel("Norwich Terrier",R.drawable.norwich,"~0,3m","~0,6kg","Anh","10 - 14 năm","Hoa Kỳ","Norwich Terrier có khả năng săn mồi cao và không bao giờ được tin tưởng để bị xích khi chúng không ở trong khu vực có hàng rào.\n" +
                "Norwich Terrier yêu cầu ít nhất hai lần đi bộ dài mỗi ngày để giữ cho chúng khỏe mạnh và giúp chúng thải ra năng lượng dư thừa. Họ là những người bạn đồng hành tuyệt vời.\n" +
                "Norwich Terriers phải có một sân hàng rào vì chúng sẽ đuổi theo bất kỳ con vật nào mà chúng cho là \"con mồi\". Đấu kiếm điện tử dưới lòng đất không phù hợp với Norwich Terriers vì chúng có xu hướng bỏ qua cú sốc.\n" +
                "Norwich Terrier có thể khó huấn luyện và mặc dù chúng rất mong muốn được làm hài lòng, việc huấn luyện có thể khó khăn khi không có động cơ phù hợp. Hãy kiên nhẫn, tuân thủ một lịch trình thường xuyên, khen thưởng và khen ngợi chúng khi chúng ngồi bô ngoài trời và đóng thùng khi bạn không thể giám sát chúng trong nhà.\n" +
                "Norwich Terrier có thể cùng tồn tại với những con chó và mèo khác, nhưng loài chó này thường phân loại bất kỳ thỏ, chuột nhảy hoặc các loài gặm nhấm nhỏ khác là con mồi. Chúng không phù hợp với những ngôi nhà mà vật nuôi nhỏ được phép đi lang thang tự do.\n" +
                "Tiếng sủa thường là một dấu hiệu cho thấy Norwich Terrier của bạn nhìn thấy điều gì đó đáng ngờ, buồn chán hoặc không được đáp ứng nhu cầu tập thể dục của mình. Giống chó này không được biết đến vì quá vui vẻ nhưng có những ngoại lệ đối với mọi quy tắc và mọi Norwich Terrier sẽ sủa nếu điều trên xảy ra.\n" +
                "Norwich Terrier có thể sống trong các căn hộ nếu chúng được vận động nhiều (tất nhiên điều này có thể nói là ở hầu hết các loài chó).\n" +
                "Giống chó sục Norwich, giống như nhiều loài chó sục khác, thích đào bới. Hãy nhớ rằng việc huấn luyện chó đào bới ở một khu vực cụ thể sẽ dễ dàng hơn khi chúng phá bỏ thói quen đào bới của chúng.\n" +
                "Norwich là một giống hiếm và do đó đắt tiền.",""));
        dogModels.add(new DogModel("Otterhound",R.drawable.otterhound,"0,6 - 0,7m","36 - 52kg","Anh","10 - 12 năm","Hoa Kỳ","Rái cá đòi hỏi phải tập luyện rất nhiều và không chỉ đuổi theo một quả bóng ở sân sau. Một tập luyện hàng ngày mạnh mẽ của chạy bộ hoặc bơi lội trong vài dặm là cần thiết để giữ anh chất và tinh thần khỏe mạnh. Tuy nhiên, vì tác dụng phụ của việc tập thể dục gắng sức đối với sự phát triển của khớp và xương, bạn nên hạn chế tập thể dục cho chó con và chó săn rái cá vị thành niên (và cả những con bị loạn sản xương hông ). Bơi lội là bài tập tốt nhất cho những chú chó nhỏ tuổi, vì nguy cơ chấn thương khớp là rất ít.\n" +
                "Otterhound là loài chó sủa nhiệt tình và ồn ào. Nhưng đừng mong đợi đó là một con chó bảo vệ - nó quá thân thiện với điều đó.\n" +
                "Không cho phép chú chó Otterhound của bạn không được xích trong những khu vực không có hàng rào; bạn không bao giờ biết khi nào anh ta có thể bắt gặp một mùi hương hấp dẫn và bỏ chạy.\n" +
                "Chó săn rái cá thích ở ngoài trời, nhưng chúng thích hợp nhất với cuộc sống hàng ngày trong nhà cùng gia đình.\n" +
                "Sân có hàng rào là bắt buộc. Otterhound được biết là có thể nhảy hàng rào cao tới 1,5m, vì vậy hãy đảm bảo hàng rào cao hơn khoảng này.\n" +
                "Otterhound rất tình cảm nhưng cũng rất độc lập. Nó sẽ không theo dõi bạn, cầu xin sự chú ý. Nó có thể sẽ chào bạn khi bạn về nhà, và sau đó - nếu nó không cần tập thể dục - nó sẽ quay trở lại vị trí báo lại yêu thích của mình.\n" +
                "Otterhound rất thích thức ăn và có thể trở nên béo phì nếu bạn không theo dõi chế độ ăn của nó . Ngoài ra, khứu giác đáng kinh ngạc của anh ấy cho phép nó xác định được những món quà đặc biệt mà bạn đã giấu trong tủ, và kích thước và sự thông minh của anh ấy giúp anh ấy tìm cách lấy chúng.\n" +
                "Con chó lớn, chi phí lớn hơn. Mọi thứ cho một con chó lớn đều tốn nhiều tiền hơn, từ thức ăn đến chải lông cho đến chăm sóc thú y.",""));
        dogModels.add(new DogModel("Papillon",R.drawable.papillon,"0,2 - 0,3m","2 - 4kg","Pháp","12 - 16 năm","Bỉ","Papillons không hoạt động tốt trong môi trường có ít thời gian dành cho chó. Họ sẽ chọn không bao giờ tách rời khỏi những người bạn đồng hành của họ.\n" +
                "Chó con mỏng manh và có thể bị thương khi chơi thô bạo và nhào lộn. Chúng không thích hợp cho những gia đình có con nhỏ.\n" +
                "Papillons là một trong những giống chó nhạy cảm với thuốc gây mê. Hãy ghi nhớ điều này khi lên lịch cho bất kỳ quy trình phẫu thuật nào.",""));
        dogModels.add(new DogModel("Papipoo",R.drawable.papipo,"~0,3m","3 - 6kg","lai giữa Papillon & Poodle","10 - 14 năm","Mỹ","Các màu của Papipoo bao gồm trắng, đen, kem, đỏ, sable, mơ và xám. Đôi khi chúng có thể có các mảng trắng lẫn lộn giữa các màu này.\n" +
                "Nếu Papipoos biểu hiện lông Poodle xoăn, chúng sẽ dễ bị dị ứng hơn. Papipoos có khả năng bảo dưỡng khá thấp, nhưng chúng có lợi khi đánh răng vài lần một tuần.\n" +
                "Vì Papipoos là những con chó có kích thước đồ chơi, nên điều đặc biệt quan trọng là trẻ em và các vật nuôi khác phải học cách nhẹ nhàng và thận trọng khi ở xung quanh chúng, vì những con chó nhỏ có thể dễ bị thương hơn.\n" +
                "Papipoo của bạn sẽ cần ít nhất một giờ tập thể dục mỗi ngày, mặc dù hầu hết thời gian này sẽ do bạn tự tạo ra bằng cách chạy, nhảy và chơi xung quanh nhà. Ít nhất một lần đi bộ với bạn mỗi ngày, ngay cả khi chỉ quanh khu phố, sẽ tốt cho họ.\n" +
                "Mặc dù chúng thường thích phụ trách, nhưng Papipoos lại làm tốt với tất cả các loại động vật khác. Khả năng săn mồi của chúng ở mức trung bình đối với loài chó, vì vậy bạn nên giám sát chúng khi chúng ở gần những con vật nhỏ hơn.\n" +
                "Vì chúng rất nhỏ, bạn không nên để Papillons không có người trông coi ở bên ngoài, cũng như không nên sống bên ngoài - chúng sẽ dễ bị tấn công bởi những kẻ săn mồi cả trên đất liền và trên không.",""));
        dogModels.add(new DogModel("Peekapoo",R.drawable.peekapoo,"~0,3m","4 - 9kg","lai giữa Pekingese với Toy hoặc Miniature Poodle","10 - 15 năm","Nhật","Các giống lai đa thế hệ (Peekapoo đến Peekapoo) rất hiếm. Nếu bạn quan tâm đến một chú chó Peekapoo, hãy hiểu rằng ngoại hình, kích thước và tính khí của chúng không thể đoán được như những chú chó thuần chủng, vì bạn không biết những đặc điểm nào của mỗi giống sẽ thể hiện ở bất kỳ con chó nhất định nào.\n" +
                "Peekapoo là một con chó năng động và tràn đầy năng lượng. Anh ấy yêu cầu tập thể dục hàng ngày và làm tốt với việc đi bộ tốt hoặc đi bộ trong sân. Đừng tập Peekapoo quá mức đến mức suy hô hấp.\n" +
                "Peekapoos có thể bị kiệt sức vì nhiệt nhanh chóng. Chúng hoạt động tốt nhất trong nhà có máy lạnh.\n" +
                "Sủa là một trò tiêu khiển yêu thích của Peekapoo. Chúng là những con chó canh gác xuất sắc và sẽ cảnh báo sủa những người hoặc những thứ mà chúng cho là đáng ngờ.\n" +
                "Một chú Peekapoo có lông chỉ cần chải lông khoảng hai lần mỗi tuần, trong khi một chú Peekapoo có bộ lông tự nhiên, đầy đủ sẽ yêu cầu chải lông hàng ngày.\n" +
                "Yêu thương và dịu dàng, Peekapoo có thể trở thành một người bạn đồng hành tuyệt vời với những đứa trẻ lớn hơn, chu đáo hơn.\n" +
                "Peekapoos thường làm tốt với những con chó và vật nuôi khác nếu chúng được giới thiệu với chúng khi còn nhỏ.\n" +
                "Peekapoos có thể dễ dàng huấn luyện với sự củng cố tích cực.\n" +
                "Với tầm vóc nhỏ bé của mình, Peekapoo có thể là một căn hộ tuyệt vời, nhưng nó hạnh phúc nhất với một khoảng sân để tận hưởng không gian ngoài trời tuyệt vời.\n" +
                "Peekapoos có thể bị lo lắng khi bị bỏ mặc một mình trong thời gian dài.",""));
        dogModels.add(new DogModel("Phu Quoc",R.drawable.phu_quoc,"0,5 - 0,6m","18 to 25kg","Phu Quoc Island, Viet Nam","10 -12 năm","Viet Nam","Thông minh và ham học hỏi, chó xoáy Phú Quốc sẽ nhanh chóng học hỏi và khiến bất kỳ chủ nhân nào cũng phải nể phục. Dũng cảm và tận tụy với gia đình, chúng sẽ không ngần ngại bảo vệ lãnh thổ của mình khi thích hợp. Chúng được biết là sẽ sủa to khi một người mới xâm nhập vào lãnh thổ của chúng, mặc dù hiếm khi chúng thể hiện sự hung dữ thực sự.\n" +
                "Bản chất hòa đồng, chúng có xu hướng hòa thuận với những con chó và trẻ em khác. Nhanh nhẹn và thể thao, chó xoáy Phú Quốc sẽ sẵn lòng đồng hành cùng bạn trong những chuyến đi bộ leo dốc, có thể dừng lại dọc đường để leo cây và khảo sát tuyến đường phía trước. Chúng sẽ không ngần ngại ngâm mình nhanh chóng nếu có bất kỳ vùng nước nào dọc theo đường mòn, chúng thích ở dưới nước.\n"+
                "Do bản tính tò mò tự nhiên và mức độ thông minh cao, chó xoáy Phú Quốc là giống chó rất dễ huấn luyện. Nói chung là thụ động, chúng rất vui khi huấn luyện viên thiết lập tốc độ và sẵn sàng làm theo hướng dẫn. Nhiều du khách du lịch đến đảo Phú Quốc cho biết họ đã nhìn thấy những con chó xoáy Phú Quốc vô danh thực hiện các trò lừa đảo với hy vọng kiếm được chút thức ăn.",""));

        dogModels.add(new DogModel("Rat Terrier",R.drawable.rat_terrier,"0,3 - 0,4m","10 -18kg","Mỹ","13 đến 18 năm","miền Nam và Trung Mỹ","Rất nhiều khách đến thăm nhà của bạn? Mặc dù hết lòng vì gia đình, Rat Terrier cần thời gian để ấm áp với người lạ.\n" +
                "Rat Terrier có rất nhiều năng lượng; bạn nên dành ít nhất 40 phút mỗi ngày để tập thể dục cho chó . Nếu bạn không làm vậy, Rat Terrier có thể trở nên phá hoại như một cách để giải phóng năng lượng bị dồn nén.\n" +
                "Chúng cũng cần được kích thích tinh thần. Một con Rat Terrier buồn chán sẽ dùng đến cách sủa và nhai.\n" +
                " Việc bắt buộc phải đuổi theo của Rat Terrier không khiến nó trở thành lựa chọn tốt nhất cho một con chó không dây. Ngay cả những con ngoan ngoãn nhất cũng có khả năng \"quên\" việc huấn luyện của chúng khi đối mặt với những con mồi trêu ngươi.",""));
        dogModels.add(new DogModel("Rhodesian Ridgeback",R.drawable.rhodesian_ridgebacks,"0,7 - 0,8m","31 - 38kg","Nam Phi","10 - 12 năm","","Rhodesian Ridgeback có khả năng chịu đựng được với trẻ em, nhưng có thể quá dai đối với trẻ mới biết đi.\n" +
                "Do kích thước, trí thông minh và sức mạnh của chúng, Rhodesian Ridgebacks không được khuyến khích cho những chủ sở hữu lần đầu hoặc nhút nhát.\n" +
                "Nếu một con chó xoáy Rhodesian được nuôi chung với những vật nuôi khác, chúng sẽ chấp nhận chúng. Tuy nhiên, chúng vẫn có thể gây hấn với những động vật lạ bên ngoài gia đình, ngay cả khi chúng được xã hội hóa và huấn luyện tốt. Con đực có thể hung hãn với con đực khác , đặc biệt là nếu họ không thiến .\n" +
                "Nếu buồn chán, Rhodesian Ridgeback có thể trở nên rất phá phách .\n" +
                "Rhodesian Ridgeback cần một hàng rào cao để ngăn nó trốn thoát và đi lang thang. Một hàng rào điện tử ngầm sẽ không chứa chúng.\n" +
                "Rhodesian Ridgebacks rụng lông rất ít, và bạn có thể giữ chúng sạch sẽ bằng cách chải lông hàng tuần và lau bằng khăn ẩm. Chúng cũng cần cắt móng và đánh răng thường xuyên .\n" +
                "Việc đào tạo có thể khó khăn nếu bạn không bắt đầu từ khi còn rất trẻ. Rhodesian Ridgebacks có thể cứng đầu và có ý chí mạnh mẽ, nhưng nếu kiên định, kiên định và công bằng, bạn có thể huấn luyện Ridgeback của mình lên một cấp độ cao.\n" +
                "Chó xoáy Rhodesian trẻ tuổi rất năng động và năng động, nhưng với sự trưởng thành và huấn luyện, nó thường trở thành một con chó điềm tĩnh và ít nói. Chúng cần ít nhất nửa giờ tập thể dục hàng ngày .\n" +
                "Rhodesian Ridgebacks có thể thích nghi với một số hoàn cảnh sống, bao gồm cả căn hộ, nếu chúng được vận động đúng cách. Lý tưởng là một ngôi nhà có sân rộng có hàng rào.\n" +
                "Chó xoáy thường không sủa nhiều. Nhiều con sẽ sủa để cảnh báo bạn về điều gì đó bất thường, và một số sẽ sủa khi chúng cảm thấy buồn chán, nhưng phần lớn, đây không phải là một giống chó ngon.\n" +
                "Rhodesian Ridgebacks không phải là những thợ đào nghiêm túc , nhưng chúng sẽ đào một cái hố lớn nếu cảm thấy buồn chán hoặc để thoát khỏi cái nóng .",""));
        dogModels.add(new DogModel("Rottweiler",R.drawable.rottweiler,"0,7 - 0,8m","38 - 60kg","Đức","8 - 11 năm","Mỹ","Rottweilers là những con chó to lớn, mạnh mẽ và cần được xã hội hóa và huấn luyện sâu rộng ngay từ khi còn nhỏ.\n" +
                "Ngay cả khi bạn huấn luyện và hòa đồng Rottweiler của mình, đôi khi sẽ phải chịu những phán xét không công bằng về con chó của bạn, thậm chí có thể bị những người sợ hãi đưa ra những cáo buộc không đúng sự thật về nó và các hoạt động của nó.\n" +
                "Do định kiến hiện tại đối với những con chó như Rottweilers và cho rằng chúng có thể nguy hiểm, bạn có thể phải mang thêm bảo hiểm trách nhiệm để sở hữu một con, tùy thuộc vào các quy định tại thị trấn của bạn. Ở một số khu vực, bạn thậm chí không thể sở hữu một con Rottweiler hoặc có thể buộc phải từ bỏ bất kỳ con nào bạn có.\n" +
                "Rottweilers yêu mọi người và muốn ở bên gia đình của hchúngọ. Nếu chúng bị bỏ mặc trong thời gian dài hoặc không được tập thể dục đầy đủ , chúng có thể trở nên phá phách .\n" +
                "Nếu được nuôi cùng con cái , những chú Rottweilers được lai tạo tốt sẽ rất hòa thuận với chúng. Tuy nhiên, chúng phải được dạy về cách cư xử có thể chấp nhận được với trẻ em. Những con thối có bản năng bầy đàn tự nhiên và có thể \"va\" con cái để bầy chúng. Do kích thước của chúng, \"vết sưng\" này có thể khiến trẻ mới biết đi ngã xuống và tự bị thương. Ngoài ra, một số Rottweilers có khả năng săn mồi mạnh và có thể bị kích động quá mức khi trẻ em chạy nhảy và chơi đùa. Luôn giám sát Rottweiler của bạn khi chúng ở gần trẻ em.\n" +
                "Nếu bạn có một con Rottweiler trưởng thành , hãy cẩn thận giới thiệu những con vật mới, đặc biệt là chó . Rottweilers có thể hung dữ với những con chó lạ, đặc biệt là những con cùng giới. Tuy nhiên, dưới sự lãnh đạo của bạn, Rottie của bạn có thể sẽ học cách chung sống hòa bình với người bạn đồng hành mới của mình.\n" +
                "Rottweilers rất thông minh và có khả năng huấn luyện cao nếu bạn kiên định và kiên định.",""));
        dogModels.add(new DogModel("Rottle",R.drawable.rottle,"0,3 - 0,7m","27 - 40kg","lai giữa Rottweilers và Poodle","9 - 15 năm","Bắc Mỹ.","Màu sắc chính của Rottles là nâu, đen, trắng, đỏ, xám và xanh lam. Đôi khi lông của chúng chắc chắn, và đôi khi chúng có sự pha trộn của nhiều màu sắc\n" +
                "Chúng có xu hướng là những con chó rụng lông thấp hơn, nhưng điều đó không có nghĩa là chúng không gây dị ứng. Chải lông kỹ mỗi ngày sẽ giúp bộ lông của Rottle khỏe mạnh.\n" +
                "Chúng tương đối dễ huấn luyện so với các giống chó lai tạo khác, miễn là việc huấn luyện bắt đầu từ khi còn nhỏ. Do trí thông minh cao, chúng có thể trở nên phá phách nếu cảm thấy buồn chán.\n" +
                "Chúng có mức năng lượng cao. Đảm bảo chó của bạn được đi bộ ít nhất từ nửa tiếng đến một tiếng mỗi ngày với một vài buổi chơi vui vẻ, tích cực và kết hợp đi bộ ngắn hơn. Hãy nhớ bao gồm một số trò chơi kích thích tinh thần.\n" +
                "Nói chung, Rottle được coi là một giống chó lai thân thiện với gia đình. Bản chất chúng có thể rất tình cảm, nhưng điều quan trọng vẫn là giám sát và dạy trẻ cách chơi với chó một cách an toàn.",""));
        dogModels.add(new DogModel("Saint Berdoodle",R.drawable.saint_berdoodle,"0,4 - 0,8m","18 - 80kg","lai giữa Poodle & Saint Bernard","8 đến 12 năm","châu Âu"," Hai sự kết hợp màu sắc phổ biến của Saint Berdoodles là trắng nâu và trắng đen. Chúng cũng có thể có màu đỏ với điểm nhấn màu trắng hoặc màu trắng với điểm nhấn màu đỏ.\n" +
                "Bộ lông của Saint Berdoodles có thể thay đổi sau khi bố hoặc mẹ - lông xù và xoăn đối với Poodle, hoặc dài hơn và mịn hoặc thô đối với Saint Bernard.\n" +
                "Saint Berdoodles thường được trang bị khá tốt cho thời tiết lạnh, đặc biệt nếu lông của chúng giống với Saint Bernards. Chúng không chịu được nhiệt độ cao.\n" +
                "Saint Berdoodles rất tuyệt vời với mọi người ở mọi lứa tuổi, kể cả trẻ em. Mối quan tâm duy nhất với những con chó này là kích thước của chúng với trẻ nhỏ. Đảm bảo giám sát tương tác với trẻ nhỏ trong trường hợp vô tình bước hoặc ngồi.\n" +
                "Những con chó này không thích ở một mình, vì vậy chúng rất thích bầu bạn với những vật nuôi khác và các thành viên trong gia đình.\n" +
                "Cảm giác phiêu lưu và năng lượng cao hơn của Poodle, kết hợp với sự tò mò và thái độ thoải mái của Saint Bernard, có nghĩa là bạn nên chuẩn bị cho chó thời gian tập thể dục ngoài trời mỗi ngày, cùng với cơ hội ngủ trưa sau đó.",""));
        dogModels.add(new DogModel("Saint Bernard",R.drawable.saint_bernard,"0,6 -0,8m","54 - 82kg","Thụy Sỹ","8 - 10 năm","Thụy Sỹ","Saint Bernard là một giống chó có kích thước khổng lồ và mặc dù chúng thường yên tĩnh bên trong, chúng không thích hợp nhất với các căn hộ . Họ cần không gian để di chuyển.\n" +
                "Nếu bạn tự cho mình là một kẻ quái đản gọn gàng, thì Saint Bernard không phải là giống chó dành cho bạn. Chúng chảy nước dãi và các bàn chân của chúng bám trên bùn. Chúng rụng lông nhiều và rụng lông, hoặc rụng lông hai lần một năm.\n" +
                "Saint Bernards thường mất nhiều thời gian hơn để trưởng thành về mặt tinh thần. Điều này để lại cho bạn một con chó con rất lớn trong vài năm.\n" +
                "Mặc dù Saint Bernards là vật nuôi tuyệt vời trong gia đình, chúng không được khuyến khích cho những nhà có trẻ nhỏ, vì chúng có thể vô tình xô ngã và làm tổn thương trẻ nhỏ.\n" +
                "Ban đầu được lai tạo để chịu được nhiệt độ lạnh giá của dãy Alps, Saint Bernard không chịu nhiệt tốt.\n" +
                "Saint Bernards không được biết đến với việc sủa vô cớ .\n" +
                "Saint Bernards là một giống chó có tuổi thọ ngắn, thường chỉ từ 8 đến 10 năm.\n" +
                "Saint Bernard không nên sống ngoài trời xa gia đình. Tất cả những chú chó đều làm tốt hơn khi chúng ở trong nhà với gia đình mà chúng yêu thương, và Saint Bernard cũng không ngoại lệ. Mặc dù lớp lông và cấu trúc của chúng khiến chúng trở thành một lựa chọn hiển nhiên cho cuộc sống ngoài trời, nhưng tính khí nóng nảy và không có khả năng chống chọi với nắng nóng khiến nó trở thành một quyết định sai lầm.\n" +
                "Nhờ sự nổi tiếng của các bộ phim như Beethoven, trong đó có Saint Bernard to lớn, nhiều nhà lai tạo và nhà máy sản xuất chó con vô trách nhiệm đã sản sinh ra những người khổng lồ hiền lành này",""));
        dogModels.add(new DogModel("Saluki",R.drawable.saluki,"0,6 - 0,7m","16 - 30kg","Iran","12 - 14 năm","Iran","Salukis thích chạy và cần tập thể dục thường xuyên hàng ngày.\n" +
                "Chúng phải được xích lại bất cứ khi nào chúng không ở trong khu vực có hàng rào an toàn. Chúng có một ổ săn mồi mạnh mẽ và sẽ truy đuổi bất cứ thứ gì có lông và đang di chuyển, bất chấp mệnh lệnh của chủ nhân.\n" +
                "Salukis là một giống chó dè dặt mặc dù chúng tận tâm với con người.\n" +
                "Xã hội hóa sớm và liên tục là rất quan trọng đối với giống chó này để ngăn chặn sự nhút nhát và lém lỉnh.\n" +
                "Salukis không được khuyến khích cho các căn hộ. Chúng yêu cầu một sân rộng có hàng rào để chúng có thể chạy an toàn. Hàng rào điện tử ngầm không được khuyến khích; ổ con mồi của chúng mạnh đến nỗi chúng sẽ vượt qua nó.\n" +
                "Điều quan trọng là phải cung cấp bộ đồ giường thoải mái cho Saluki vì anh ta không có đủ lượng mỡ trong cơ thể để cung cấp đệm.\n" +
                "Salukis không nên sống ngoài trời. Chúng phát triển mạnh nhờ sự đồng hành của con người và sẽ trở nên trầm cảm nếu bị bỏ mặc trong thời gian dài.\n" +
                "Mặc dù những con chó này có thể làm bạn đồng hành nhẹ nhàng và điềm tĩnh cho trẻ lớn hơn, chúng không được khuyến khích cho những nhà có trẻ nhỏ.\n" +
                "Salukis nói chung là những con chó yên tĩnh.",""));
        dogModels.add(new DogModel("Valley Bulldog",R.drawable.valley_bulldog,"0,3 - 0,6m","25 - 55kg","Nova Scotia, Canada","8 - 12 năm","Nova Scotia, Canada","Valley Bulldog là một giống chó hỗn hợp. Chúng không phải là những con thuần chủng như bố mẹ Boxer hoặc Bulldog Anh.\n" +
                "Màu sắc chính của Valley Bulldogs là; đỏ, rám nắng, vện, trắng và nâu vàng. Hiếm khi rắn, áo khoác của họ thường có sự pha trộn của hai hoặc nhiều màu.\n" +
                "Chúng thường có lớp lông ngắn và thường không được coi là dễ gây dị ứng. May mắn thay, lông của họ rất dễ chải chuốt.\n" +
                "Valley Bulldogs yêu trẻ em và là bạn chơi tuyệt vời cho những đứa trẻ lớn hơn năng động. Tuy nhiên, chúng có thể quá cứng cáp đối với trẻ mới biết đi và vô tình có thể đánh gục chúng khi chơi. Luôn giám sát thời gian chơi.",""));
        dogModels.add(new DogModel("Vizsla",R.drawable.vizsla,"0,5 - 0,6m","20 - 30kg","Hungary","10 - 14 năm","Hungary","Vizslas là một giống chó năng động và cần ít nhất 60 phút tập thể dục mỗi ngày. Chúng thích đi bộ đường dài, chạy bộ, và chơi ném bóng cũng như các môn thể thao dành cho chó.\n" +
                "Vizslas là loại lông rụng ít đến trung bình và chỉ cần chải lông hàng tuần để chúng không bị rụng lông. Chúng hiếm khi cần tắm và không có mùi chó nặng.\n" +
                "Vizslas phát triển mạnh nhờ sự đồng hành của con người. Họ sẽ đi theo các thành viên trong gia đình từ phòng này sang phòng khác và thích được người của họ chạm vào hoặc chạm vào.\n" +
                "Vizslas không được khuyến khích cho những người làm việc nhiều giờ. Vizslas có thể bị chứng lo lắng khi không co ai bên cạnh, có thể dẫn đến các hành vi phá hoại .\n" +
                "Vizslas có xu hướng là nhai đồ. Giữ cho Vizsla của bạn được cung cấp nhiều đồ chơi nhai để bảo vệ tài sản của bạn.\n" +
                "Vizslas hoạt động tốt nhất trong những ngôi nhà có sân hàng rào, nơi chúng có thể chạy và chơi một cách an toàn.\n" +
                "Vizslas nên sống trong nhà với gia đình, không phải bên ngoài. Bộ lông của chúng không thể bảo vệ chúng khỏi nhiệt độ lạnh và chúng không thể phát triển nếu không có sự đồng hành của con người.\n" +
                "Mặc dù chúng không được khuyên dùng cho những ngôi nhà có trẻ nhỏ, nhưng Vizslas rất tình cảm với trẻ em và có thể trở thành người bạn đồng hành tuyệt vời cho những đứa trẻ lớn tuổi, năng động.\n" +
                "Việc đào tạo và xã hội hóa là điều bắt buộc với giống chó này. Chúng có thể khó xử lý nếu chúng không được đào tạo đúng cách và chúng có thể trở nên nhút nhát và rụt rè nếu không được hòa nhập xã hội đúng cách.\n" +
                "Vizslas hòa thuận tốt với những con chó khác và thậm chí sẽ hòa thuận với mèo nếu được nuôi chung với chúng. Tuy nhiên, chúng không phù hợp với những ngôi nhà có vật nuôi nhỏ như thỏ, chuột nhảy, chuột lang hoặc chim.",""));
        dogModels.add(new DogModel("Weimaraner",R.drawable.weimaraner,"0,6 - 0,7m","25 - 40kg","Đức","11 đến 13 năm","Đức","Weimaraners được lai tạo để có rất nhiều năng lượng và sức chịu đựng. Hãy chuẩn bị để cung cấp cho chúng nhiều bài tập thể dục và kích thích tinh thần.\n" +
                "Weims không phải là một con chó miệng mềm như Golden Retriever và một số có khả năng chịu đựng thấp đối với động vật nhỏ, nhiều lông, chẳng hạn như thỏ, và thậm chí cả mèo và chó. Cho đến khi bạn hiểu rõ về con chó của mình, hãy quan sát nó cẩn thận khi có những động vật nhỏ ở bên cạnh.\n" +
                "Weims là những con chó có sức sống cao và có thể bị chứng lo lắng chia ly nghiêm trọng . Nếu để ở một mình quá lâu, chúng có thể sủa , phá phách hoặc thậm chí tự làm mình bị thương.\n" +
                "Mặc dù Weimaraners là loài chó săn, chúng không thích sống ngoài trời. Họ đòi hỏi rất nhiều sự chú ý và muốn ở gần bạn.\n" +
                "Weims thường nghi ngờ người lạ và có thể hung hăng không thể chấp nhận được. Giao lưu chúng với nhiều người và nhiều tình huống khác nhau khi chúng còn là những chú chó con và trong suốt cuộc đời của chúng là rất quan trọng.\n" +
                "Người Weimaraners thông minh và họ nghĩ cho chính mình. Công ty, phù hợp, nhẹ nhàng đào tạo phải tiếp tục trong suốt cuộc sống của họ.\n" +
                "Người Weimaraners có thể khó bắt mồi. Đào tạo thùng được khuyến khích.\n" +
                "Các nhà lai tạo không có đạo đức có thể quảng cáo những con Weimara màu xanh hoặc đen là \"hiếm\" để thu hút người mua và sẽ tính phí cao hơn cho những con có màu này, nhưng sự thật là những con Weimaraners màu xanh và đen không đủ tiêu chuẩn trong tiêu chuẩn giống.",""));
        dogModels.add(new DogModel("Xoloitzcuintli",R.drawable.xoloitzcuintli,"0,6 - 0,8m","5 - 25kg","Mexico","14 - 20 năm","châu Á","Xolos có ba kích cỡ khác nhau, do đó, giống chó này có thể thích nghi với mọi kiểu nhà.\n" +
                "Có nguồn gốc từ Mexico, Xolo còn được gọi là Người Mexico không lông.\n" +
                "Xolo được cho là có niên đại từ các nền văn minh tiền Colombia.\n" +
                "Mặc dù chúng được biết đến là một giống chó không có lông, nhưng Xolo cũng có nhiều loại khác nhau.\n" +
                "Cơ thể của Xolo dài hơn một chút so với chiều cao.\n" +
                "Ngoài vai trò là một người bạn đồng hành tuyệt vời, Xolo còn là một chú chó canh gác bảo vệ.\n" +
                "Việc Xolo không có lớp lông cách nhiệt khiến chúng cảm thấy ấm áp khi chạm vào, mặc dù nhiệt độ cơ thể của chúng không cao hơn bất kỳ giống chó nào.\n" +
                "Xolo đã được American Kennel Club công nhận vào năm 2011 với tư cách là thành viên của Non-Sporting Group.\n" +
                "Có ít hơn 1.000 Xolos ở Hoa Kỳ, với khoảng 30.000 trên toàn thế giới.\n" +
                "Xolo không gây dị ứng, mặc dù cơ thể không có lông của chúng có thể ít gây dị ứng ở những người nhạy cảm.\n" +
                "Xolo có thể có một ổ săn mồi mạnh mẽ và có khả năng đuổi theo các động vật khác.",""));
        dogModels.add(new DogModel("Yorkipoo",R.drawable.yorkipoo,"0,2 - 0,4m","1,5 - 6kg","lai giữa Yorkshire Terrier và Toy hoặc Miniature Poodle","10 - 15 năm","Hungary","Yorkipoo là một giống chó có thiết kế riêng. Đã có sự gia tăng trong việc nhân giống đa thế hệ (Yorkipoo đến Yorkipoo), và cũng có ở Yorkipoo với Poodle hoặc Yorkipoo với Yorkshire Terrier lai tạo; nhưng nhiều lứa là thế hệ thứ nhất, là kết quả của việc lai tạo hai bố mẹ thuần chủng.\n" +
                "Một con Yorkipoo năng động và tràn đầy năng lượng, cả Poodles và Yorkies cũng vậy. Chúng yêu cầu tập thể dục hàng ngày và làm tốt với việc đi bộ tốt hoặc đi bộ trong sân.\n" +
                "Sủa là một trò tiêu khiển yêu thích. Đôi khi một con Yorkipoo có thể được huấn luyện để ít sủa hơn, nhưng mong đợi sẽ nghe thấy tiếng ồn bất cứ khi nào có người đến mở cửa. Chúng không có manh mối nào cho thấy tiếng sủa của mình không làm ai khiếp sợ.\n" +
                "Anh ấy là một người không rụng lông và có thể trở thành một người bạn đồng hành tuyệt vời cho những người bị dị ứng .\n" +
                "Cần chải lông hàng ngày để giữ cho bộ lông mịn và mượt của chúng không bị rối và bết.\n" +
                "Yêu thương và dịu dàng, Yorkipoo có thể trở thành một người bạn đồng hành tuyệt vời với những đứa trẻ lớn hơn, chu đáo hơn. Giống như hầu hết các giống chó đồ chơi, chúng không được khuyến khích cho những nhà có trẻ nhỏ.\n" +
                "Chúng rất dễ huấn luyện nếu bạn sử dụng biện pháp củng cố tích cực. Tuy nhiên, chúng có một tính cách cứng đầu, vì vậy hãy mong đợi một số phản kháng thường xuyên.\n" +
                "Yorkipoo có thể sống rất hạnh phúc trong một căn hộ.\n" +
                "Anh ấy thường làm tốt với những con chó và vật nuôi khác.\n" +
                "Một con chó đồng hành, nó có thể bị lo lắng về sự chia ly khi bị bỏ rơi một mình trong thời gian dài.",""));
        dogModels.add(new DogModel("Dutch Shepherd",R.drawable.dutch_shepherd,"0,5 - 0,6m","22 - 30kg","Hà Lan","12 - 15 năm","Hà Lan","Ban đầu được sử dụng bởi những người chăn cừu ở Hà Lan cho công việc đồng áng, Dutch Shepherd là một giống chó chăn cừu thông minh, có khả năng huấn luyện cao. Trong thời hiện đại, họ tìm việc làm chó cảnh sát, động vật phục vụ và những người bạn đồng hành thân thiện với gia đình\n" +
                "Bạn có thể nhìn vào Dutch Shepherd và nhận thấy một sự giống nhau nổi bật với Shepherd Đức hoặc Shepherd Bỉ. Đó là bởi vì những giống chó anh em họ này có chung một tổ tiên gần gũi và chỉ khác nhau hơn một trăm năm trước. Mặc dù Dutch Shepherd hiếm hơn nhiều giống chó chăn cừu khác, nhưng chúng được biết đến là một số giống chó khỏe mạnh và dễ huấn luyện nhất.\n"+
                "Bộ lông của Dutch Shepherd giúp chúng thoải mái trong cả thời tiết nóng và lạnh, mặc dù da và lông của chúng sẽ cần một số chú ý nếu chúng sống trong khí hậu khô. Hướng đến nhu cầu tập thể dục của Dutch Shepherd và cung cấp cho chúng sự huấn luyện tự tin, và bạn sẽ có một người bạn thân yêu thương, ngoan ngoãn suốt đời\n"+
                "Chúng có khả năng sống trong căn hộ, miễn là chúng được đi bộ ít nhất một quãng đường dài và vài buổi chơi trong ngày. Xã hội hóa sớm sẽ giúp chúng giữ bình tĩnh xung quanh vật nuôi và người mới",""));
        dogModels.add(new DogModel("Toy Poodle",R.drawable.toy_poodle,"~0,3m","3 - 6 kg","Tây Âu","14 - 15 năm","","Chó Toy Poodle là giống chó có vẻ ngoài quý phái với mõm thon và cổ dài. Bộ lông dày, xoăn và thường được tạo kiểu, và có nhiều sắc thái khác nhau, bao gồm xanh lam, xám, bạc, nâu, mơ và kem.\n"+
                "Đây là một con chó hoạt bát và tình cảm, thông mnih thứ 2 trong các loài chó, có thể làm bạn đồng hành tuyệt vời nhất. Nó có thể là một con chó bảo vệ tốt, thông báo cho du khách, nhưng nói chung là tốt tính. Toy Poodles rất nhạy cảm với ngữ điệu giọng nói và phản ứng tốt với quá trình huấn luyện. Trẻ em nên được huấn luyện để chăm sóc những con chó nhỏ, mỏng manh này một cách cẩn thận\n"+
                "Đây là một giống chó khá năng động, thích đi dạo và đặc biệt thích trò chơi. Bạn nên đăng ký tham gia một số hoạt động để trí óc được kích thích cùng với cơ thể. Toy Poodles có thể làm tốt các môn thể thao vâng lời, nhanh nhẹn và hầu hết các môn thể thao của chó.",""));
        dogModels.add(new DogModel("Miniature Poodle",R.drawable.miniature_poodle,"0,3 - 0,4m","6 - 8kg","Tây Âu","12 - 15 năm","","Miniature Poodle là một giống chó lông xoăn đẹp. Ban đầu được nuôi để làm bạn đồng hành đi săn, giờ đây chúng trở thành vật nuôi trung thành và những con chó nhanh nhẹn thông minh.\n"+
                "Vì chúng rất thông minh và mong muốn được làm hài lòng nên việc huấn luyện tương đối dễ dàng. Tuy nhiên, sự thông minh đó đòi hỏi bạn phải kiên định và tích cực trong nỗ lực rèn luyện.\n"+
                "",""));
        dogModels.add(new DogModel("Standard Poodle",R.drawable.standard_poodle,"0,4 - 0,6m","20 - 35kg","Tây Âu","11 - 13 năm","","Chó Poodles tiêu chuẩn là giống chó lớn nhất và được biết đến là giống chó tình cảm, hoạt bát và rất thông minh. Chúng là những vật nuôi tuyệt vời trong gia đình và những người bạn đồng hành nhờ bản tính trung thành và tốt bụng. Hiếm khi thể hiện bất kỳ hành vi hung dữ nào, Standard Poodles là những con chó có tinh thần cao và không yêu gì hơn là làm hài lòng, đó chỉ là một trong những lý do tại sao chúng rất dễ huấn luyện khi được xử lý đúng cách\n" +
                "Standard Poodles sớm cho bạn biết khi có người lạ đến ở và không yêu gì hơn là trở thành một phần của gia đình và tham gia vào mọi việc diễn ra trong nhà. Trong những năm qua, những con chó thanh lịch này đã tìm thấy đường vào trái tim và ngôi nhà của nhiều người ở Anh và các nơi khác trên thế giới. \n" +
                "Chúng luôn thành công vang dội trong các buổi biểu diễn và thường được nhìn thấy khi tham gia các hoạt động thể thao dành cho chó mà chó Standard Poodle thực sự thích và thực sự vượt trội\n" +
                "Standard Poodle là giống chó cân đối, quý phái, có bộ lông xoăn dày. Chúng là những con chó mạnh mẽ, cơ bắp nhưng chúng di chuyển rất uyển chuyển, đó chỉ là một trong những lý do khiến giống chó này luôn thành công rực rỡ và gặt hái được nhiều thành công trên sàn đấu.\n"+
                "Giống chó này phù hợp nhất với một hộ gia đình để giúp chúng giải trí, ngay cả khi trong thời gian ngắn.",""));

        MainActivity.dogModels = dogModels;
        return dogModels;
    }


    public void btnCloseSearch_Clicked(View view) {
    }
}