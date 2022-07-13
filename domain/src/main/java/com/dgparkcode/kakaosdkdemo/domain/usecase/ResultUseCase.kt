package com.dgparkcode.kakaosdkdemo.domain.usecase

import com.dgparkcode.kakaosdkdemo.domain.utils.Result

interface ResultUseCase<DATA> : UseCase<Result<DATA>>