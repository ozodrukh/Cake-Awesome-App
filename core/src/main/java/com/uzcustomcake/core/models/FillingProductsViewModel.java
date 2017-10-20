package com.uzcustomcake.core.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.uzcustomcake.core.CoreApplication;
import com.uzcustomcake.core.domain.Bakery;
import com.uzcustomcake.core.domain.Filling;
import com.uzcustomcake.core.service.FillingsService;
import java.util.List;

/**
 * created at 10/13/17
 *
 * @author Ozodrukh
 * @version 1.0
 */
public class FillingProductsViewModel extends AndroidViewModel implements FillingsService {
  public FillingProductsViewModel(Application application) {
    super(application);
  }

  @Override public LiveData<List<String>> getFillingsTypesByProduct(Bakery product) {
    return this.<CoreApplication>getApplication().firebaseService()
        .getFillingsTypesByProduct(product);
  }

  @Override
  public LiveData<List<Filling>> getFillingsByProduct(Bakery product, String type) {
    return this.<CoreApplication>getApplication().firebaseService()
        .getFillingsByProduct(product, type);
  }
}
