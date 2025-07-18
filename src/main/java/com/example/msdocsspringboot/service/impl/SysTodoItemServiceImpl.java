package com.example.msdocsspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.msdocsspringboot.entity.TodoItem;
import com.example.msdocsspringboot.mapper.SysTodoItemMapper;
import com.example.msdocsspringboot.service.ITodoItemService;
import org.springframework.stereotype.Service;


@Service
public class SysTodoItemServiceImpl extends ServiceImpl<SysTodoItemMapper, TodoItem> implements ITodoItemService {

}
