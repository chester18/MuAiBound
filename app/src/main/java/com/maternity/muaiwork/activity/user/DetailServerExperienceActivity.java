package com.maternity.muaiwork.activity.user;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.MeteoconsModule;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;
import com.joanzapata.iconify.fonts.WeathericonsModule;
import com.joanzapata.iconify.widget.IconTextView;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.BaseDialog;
import com.maternity.muaiwork.dialog.DialogCallBackInterface;
import com.maternity.muaiwork.dialog.DialogFactry;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.ServerExperienceModel;
import com.maternity.muaiwork.view.ButtonCell;
import com.maternity.muaiwork.view.IcoCell;
import com.mob.MobApplication;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_nomal_list)
public class DetailServerExperienceActivity extends CBaseActivity {
    @ViewInject(R.id.list_title)
    TextView title;
    @ViewInject(R.id.nomal_list)
    ListView lv;
    @ViewInject(R.id.list_add)
    IconTextView itv;
    ServerExperienceModel model;            //数据模型
    String updataUrl;               //上传地址
    String[] mainTitle={"服务对象:","服务内容:","服务时间:","联系电话:",""};
    String[] keys={"ServerObject","ServerContent","ServerDuring","ServerMobile"};
    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return mainTitle.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (i==mainTitle.length-1)
            {
                ButtonCell bcell=new ButtonCell(DetailServerExperienceActivity.this);
                bcell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT));
                return bcell;
            }
            IcoCell cell=new IcoCell(DetailServerExperienceActivity.this);

                JsonObject mj = model.getJsonObject();
                if (PubFunction.hasJsonObject(mj, keys[i])) {
                    cell.setModel(new DetailCellModel(null, mainTitle[i], model.getJsonObject().get(keys[i]).getAsString(), null, "{md-chevron-right}"));
                }else {
                    cell.setModel(new DetailCellModel(null, mainTitle[i], "", null, "{md-chevron-right}"));
                }

            cell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT));
            cell.setBackgroundResource(R.color.CW);
            return cell;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        itv.setVisibility(View.INVISIBLE);
        model= (ServerExperienceModel) getIntent().getSerializableExtra("model");
        if (model==null)
        {
            model=new ServerExperienceModel(new JsonObject());
            model.userId= PubFunction.userid+"";
            updataUrl=CommonNetString.userAddServer;
        }else {
            updataUrl=CommonNetString.userUpdateServer;
        }
        title.setText("服务经历");
        lv.setAdapter(adapter);
    }
    @Event(value = R.id.backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
    @Event(value = R.id.nomal_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        int tag=position;
        switch (position){
            case 0:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 0, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.ServerObject=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 1:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 1, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.ServerContent=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 2:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 2, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.ServerDuring=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 3:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_DATE, 3, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.ServerMobile=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }


            default:
                sendPost(updataUrl,model.getJsonObject(),"信息提交中...",100);
                break;

        }

    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);

        if(tag==100)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                showToast("更新完成!");
                finish();
            }else {
                showToast("网络请求失败!");
            }
        }

    }


    /**
     * Created by apple on 16/10/12.
     */

    public static class MuAiApplication extends MobApplication {
        @Override
        public void onCreate() {
            super.onCreate();
            x.Ext.init(this); // 这一步之后, 我们就可以在任何地方使用x.app()来获取Application的实例了.
            x.Ext.setDebug(true); // 是否输出debug日志
    //        AbAppConfig.UI_WIDTH=1080;
    //        AbAppConfig.UI_HEIGHT=1920;
            //图标
            Iconify
                    .with(new FontAwesomeModule())
                    .with(new EntypoModule())
                    .with(new TypiconsModule())
                    .with(new MaterialModule())
                    .with(new MaterialCommunityModule())
                    .with(new MeteoconsModule())
                    .with(new WeathericonsModule())
                    .with(new SimpleLineIconsModule())
                    .with(new IoniconsModule());
        }
    }
}
