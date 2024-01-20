package com.project.zipkok.common.service;

import com.project.zipkok.common.exception.NoExistUserException;
import com.project.zipkok.common.oauth.RequestOAuthInfoService;
import com.project.zipkok.common.oauth.request.OAuthLoginParams;
import com.project.zipkok.common.oauth.response.OAuthInfoResponse;
import com.project.zipkok.dto.GetLoginResponse;
import com.project.zipkok.model.User;
import com.project.zipkok.repository.UserRepository;
import com.project.zipkok.util.jwt.AuthTokens;
import com.project.zipkok.util.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.zipkok.common.response.status.BaseExceptionResponseStatus.KAKAO_LOGIN_NEED_REGISTRATION;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public GetLoginResponse login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);

        User user = userRepository.findByEmail(oAuthInfoResponse.getEmail());

        if (user != null) {
            AuthTokens authTokens = jwtProvider.createToken(user.getEmail(), user.getUserId());
            return new GetLoginResponse(true, authTokens, null);
        }

        GetLoginResponse getLoginResponse = new GetLoginResponse(false, null, oAuthInfoResponse.getEmail());
        throw new NoExistUserException(KAKAO_LOGIN_NEED_REGISTRATION, getLoginResponse);

    }

//    private String findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
//
//        if(userRepository.findByEmail(oAuthInfoResponse.getEmail()) != null) {
//            return oAuthInfoResponse.getEmail();
//        }
//
//        return newMember(oAuthInfoResponse);
//    }

//    private String newMember(OAuthInfoResponse oAuthInfoResponse) {
//        User user = User.builder()
//                .email(oAuthInfoResponse.getEmail())
//                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
//                .build();
//
//        DesireResidence desireResidence = new DesireResidence(user);
//        TransactionPriceConfig transactionPriceConfig = new TransactionPriceConfig(user);
//        user.setDesireResidence(desireResidence);
//        user.setTransactionPriceConfig(transactionPriceConfig);
//
//        return userRepository.save(user).getEmail();
//    }
}
