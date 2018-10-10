package com.apap.tutorial5.service;

import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.DealerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
    @Autowired
    private DealerDb dealerDb;

    @Override
    public Optional<DealerModel> getDealerDetailById(Long id){
        return dealerDb.findById(id);
    }

    @Override
    public List<DealerModel> getAllDealer(){ return dealerDb.findAll(); }

    @Override
    public void addDealer(DealerModel dealer) {
        dealerDb.save(dealer);
    }

    @Override
    public void deleteDealer(DealerModel dealer){ dealerDb.delete(dealer);}

    @Override
    public void updateDealer(Long dealerId, DealerModel newDealer) {
        DealerModel dealer = getDealerDetailById(dealerId).get();
        dealer.setAlamat(newDealer.getAlamat());
        dealer.setNoTelp(newDealer.getNoTelp());
    }
}
