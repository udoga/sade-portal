package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.ReceiptCategory;

public interface ReceiptCategoryRepo extends Repo<ReceiptCategory> {
    List<ReceiptCategory> listSummarized();
}
